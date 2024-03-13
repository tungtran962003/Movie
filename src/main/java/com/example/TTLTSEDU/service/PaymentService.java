package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Bill;
import com.example.TTLTSEDU.entity.Users;
import com.example.TTLTSEDU.security.service.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public interface PaymentService {

    JSONObject vnpayCreate(HttpServletRequest req, Long price, String name, String phoneNumber, String email, Integer promotion) throws UnsupportedEncodingException;


    String payment(Bill bill, Users users, HttpSession httpSession);
}
