package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Bill;
import com.example.TTLTSEDU.security.service.UserDetailsImpl;
import jakarta.servlet.http.HttpSession;

public interface BillService {

    String checkOut(String movieName, Integer cinemaId, Integer roomId, Integer scheduleId, Integer foodId, Integer foodQuantity, Integer ticketId, Integer ticketQuantity, Integer promotionId, UserDetailsImpl userDetails, HttpSession httpSession);

    Bill getBillByCustomer(Integer customerId);
}
