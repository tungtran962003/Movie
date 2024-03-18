package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.*;
import com.example.TTLTSEDU.repository.*;
import com.example.TTLTSEDU.security.service.UserDetailsImpl;
import com.example.TTLTSEDU.service.BillService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BillFoodRepository billFoodRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BillTicketRepository billTicketRepository;

    public String generateCode() {
        Bill billFinalPresent = billRepository.findTopByOrderByIdDesc();
        if (billFinalPresent == null) {
            return "B00001";
        }
        Integer idFinalPresent = billFinalPresent.getId() + 1;
        String code = String.format("%05d", idFinalPresent);
        return "B" + code;
    }

    public Double calculatePriceToPriceDiscount(Double price, Integer value) {
        Double discount = price * value / 100.0;
        Double priceFinal = price - discount;
        return priceFinal;
    }

    @Override
    public String checkOut(String movieName, Integer cinemaId, Integer roomId, Integer scheduleId, Integer foodId, Integer foodQuantity, Integer ticketId, Integer ticketQuantity, Integer promotionId, UserDetailsImpl userDetails, HttpSession httpSession) {
        Food food = foodRepository.findById(foodId).orElse(null);
        Cinema cinema = cinemaRepository.findById(cinemaId).orElse(null);
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (promotionId != null) {
            Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
            if (promotion == null) {
                return "This promotion is not available";
            } else if (!promotion.getIsActive()) {
                return "This promotion has expired";
            } else if (promotion.getStartTime().compareTo(new Date()) < 0) {
                return "This promotion has not yet expired";
            } else if (promotion.getQuantity() == 0) {
                return "This promotion has ended";
            }
        }
        Double totalMoney = (food.getPrice() * foodQuantity) + (ticket.getPriceTicket() * ticketQuantity) + schedule.getPrice();
        Bill bill = createBill(movieName, promotionId, userDetails, totalMoney, cinema, room, schedule, food, ticket, httpSession);
        BillFood billFood = new BillFood();
        billFood.setBill(bill);
        billFood.setFood(food);
        billFood.setQuantity(foodQuantity);
        billFoodRepository.save(billFood);
        BillTicket billTicket = new BillTicket();
        billTicket.setBill(bill);
        billTicket.setTicket(ticket);
        billTicket.setQuantity(ticketQuantity);
        billTicketRepository.save(billTicket);
        return "Invoice created successfully";
    }

    public Bill createBill(String movieName, Integer promotionId, UserDetailsImpl userDetails, Double totalMoney, Cinema cinema, Room room, Schedule schedule, Food food, Ticket ticket, HttpSession httpSession) {
        Bill bill = new Bill();
        bill.setTradingCode(generateCode());
        bill.setIsActive(true);
        bill.setCreateTime(new Date());
        bill.setUpdateTime(new Date());
        bill.setBillStatus(BillStatus.builder().id(2).build());
        bill.setName(movieName + " movie bill");
        if (promotionId == null) {
            bill.setPromotion(null);
            bill.setTotalMoney(totalMoney);
        } else {
            Promotion promotion = promotionRepository.findById(promotionId).orElse(null);
            bill.setPromotion(Promotion.builder().id(promotionId).build());
            bill.setTotalMoney(calculatePriceToPriceDiscount(totalMoney, promotion.getPercents()));
            promotion.setQuantity(promotion.getQuantity() - 1);
            promotionRepository.save(promotion);
        }
        httpSession.setAttribute("movieName", movieName);
        httpSession.setAttribute("cinemaName", cinema.getNameOfCinema());
        httpSession.setAttribute("roomName", room.getName());
        httpSession.setAttribute("scheduleName", schedule.getName());
        httpSession.setAttribute("foodName", food.getNameOfFood());
        httpSession.setAttribute("foodName", food.getNameOfFood());
        bill.setCustomerId(userDetails.getId());
        return billRepository.save(bill);
    }

    @Override
    public Bill getBillByCustomer(Integer customerId) {
        return billRepository.findTopByCustomerIdAAndBillStatusOrderByIdDesc(customerId).get(0);
    }
}
