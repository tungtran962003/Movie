package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.config.VNPaymentConfig;
import com.example.TTLTSEDU.entity.Bill;
import com.example.TTLTSEDU.entity.BillStatus;
import com.example.TTLTSEDU.entity.Promotion;
import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.repository.BillRepository;
import com.example.TTLTSEDU.repository.PromotionRepository;
import com.example.TTLTSEDU.security.service.UserDetailsImpl;
import com.example.TTLTSEDU.service.PaymentService;
import com.example.TTLTSEDU.util.MailUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public JSONObject vnpayCreate(HttpServletRequest req, Long price, String name, String phoneNumber, String email, Integer promotionId) throws UnsupportedEncodingException {
        String vnp_Version = VNPaymentConfig.vnp_Version;
        String vnp_Command = VNPaymentConfig.vnp_Command;
        String orderType = VNPaymentConfig.orderType;
        long amount = price * 100;
        String bankCode = VNPaymentConfig.bankCode;

        String vnp_TxnRef = VNPaymentConfig.getTransactionNumber(8);
        String vnp_IpAddr = VNPaymentConfig.getIpAddress(req);

        String vnp_TmnCode = VNPaymentConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Movie - Payment orders #" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", VNPaymentConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = VNPaymentConfig.hmacSHA512(VNPaymentConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String payUrl = VNPaymentConfig.vnp_PayUrl + "?" + queryUrl;
        String promotionStr = null;
        if (promotionId != null) {
            Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
            promotionStr = promotion.getName();
        } else {
            promotionStr = null;
        }
        JSONObject returnJson = new JSONObject();
        returnJson.append("payUrl", payUrl);
        returnJson.append("name", name);
        returnJson.append("phoneNumber", phoneNumber);
        returnJson.append("email", email);
        returnJson.append("promotion", promotionStr);
        return returnJson;
    }

    @Override
    public String payment(Bill bill, Users users, HttpSession httpSession) {
        bill.setBillStatus(BillStatus.builder().id(1).build());
        Promotion promotion = bill.getPromotion();
        if (promotion != null) {
            Promotion promotion1 = promotionRepository.findById(promotion.getId()).orElse(null);
            if (promotion1.getQuantity() == 0) {
                bill.setPromotion(null);
                return "Promotion has ended";
            } else {
                promotion1.setQuantity(promotion1.getQuantity() - 1);
                promotionRepository.save(promotion1);
            }
        }
        billRepository.save(bill);
        String body =
                "Hi " + users.getName() + ",\n\n" +
                        "You have successfully paid " + bill.getName() +
//                        " Movie name: " + httpSession.getAttribute("movieName").toString() + ".\n" +
//                        " Cinema name: " + httpSession.getAttribute("cinemaName").toString() + ".\n" +
//                        " Room name: " + httpSession.getAttribute("roomName").toString() + ".\n" +
//                        " Schedule name: " + httpSession.getAttribute("scheduleName").toString() + ".\n" +
                        " with the money of " + bill.getTotalMoney() + ".\n\n" +
                        "Thank you.\n\n";
        mailUtil.sendEmail(users.getEmail(), "Your invoice", body);
//        httpSession.removeAttribute("movieName");
//        httpSession.removeAttribute("cinemaName");
//        httpSession.removeAttribute("roomName");
//        httpSession.removeAttribute("scheduleName");
        return "Payment success";
    }
}
