package com.example.TTLTSEDU.controller.common;

import com.example.TTLTSEDU.entity.Bill;
import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.response.VNPayResponse;
import com.example.TTLTSEDU.security.service.UserDetailsImpl;
import com.example.TTLTSEDU.service.BillService;
import com.example.TTLTSEDU.service.PaymentService;
import com.example.TTLTSEDU.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping
public class BillController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BillService billService;

    @Autowired
    private UsersService usersService;

    JSONObject jsonObject = new JSONObject();

    @PostMapping("/payment")
    public ResponseEntity<?> callAPIVNPay(@RequestParam(required = false) Integer promotion,
                                          HttpServletRequest request) throws UnsupportedEncodingException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Bill bill = billService.getBillByCustomer(userDetails.getId());
        jsonObject = paymentService.vnpayCreate(request, bill.getTotalMoney().longValue(), userDetails.getName(), userDetails.getPhoneNumber(), userDetails.getEmail(), promotion);
        return ResponseEntity.ok(jsonObject.get("payUrl").toString());
    }

    @PostMapping("/createBill")
    public ResponseEntity<?> createBill(@RequestParam String movieName,
                                        @RequestParam Integer cinemaId,
                                        @RequestParam Integer roomId,
                                        @RequestParam Integer scheduleId,
                                        @RequestParam Integer foodId,
                                        @RequestParam Integer foodQuantity,
                                        @RequestParam Integer ticketId,
                                        @RequestParam Integer ticketQuantity,
                                        @RequestParam(required = false) Integer promotionId,
                                        HttpSession httpSession) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String result = billService.checkOut(movieName, cinemaId, roomId, scheduleId, foodId, foodQuantity, ticketId, ticketQuantity, promotionId, userDetails, httpSession);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/vnpay/return")
    public ResponseEntity<?> payment(VNPayResponse vnPayResponse, HttpSession httpSession) {
        if (vnPayResponse.getVnp_ResponseCode().equals("00")) {
            String email = jsonObject.get("email").toString().replace("\"", "").replace("[", "").replace("]", "");
            Users users = usersService.getUserByEmail(email);
            Bill bill = billService.getBillByCustomer(users.getId());
            String result = paymentService.payment(bill, users, httpSession);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok("Unpaid payment");
        }
    }
}
