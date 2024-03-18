package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.repository.CinemaRepository;
import com.example.TTLTSEDU.repository.FoodRepository;
import com.example.TTLTSEDU.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth/statistical")
public class StatisticalController {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/bestSellingFood")
    public ResponseEntity<?> bestSellingFood() {
        return ResponseEntity.ok(foodRepository.bestSellingFood());
    }

    @GetMapping("/totalRevenue")
    public ResponseEntity<?> totalRevenue(@RequestParam(required = false) String startDate,
                                          @RequestParam(required = false) String endDate) throws ParseException {
        if ("".equals(startDate) && "".equals(endDate)) {
            return ResponseEntity.ok(cinemaService.getTotalMoneyByCinema("", ""));
        }
        else if (cinemaService.checkDate(startDate, endDate)) {
            return ResponseEntity.ok(cinemaService.getTotalMoneyByCinema(startDate, endDate));
        } else {
            return ResponseEntity.ok("Start date or End date is in wrong format (yyyy-MM-dd HH:mm:ss)");
        }
    }
}
