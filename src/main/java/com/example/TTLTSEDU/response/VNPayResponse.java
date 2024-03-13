package com.example.TTLTSEDU.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VNPayResponse {

    private String vnp_ResponseCode;
    private String vnp_CardType;
    private String vnp_Amount;
    private String vnp_TransactionNo;
    private String vnp_PayDate;
    private String vnp_OrderInfo;
    private String vnp_BankTranNo;
}
