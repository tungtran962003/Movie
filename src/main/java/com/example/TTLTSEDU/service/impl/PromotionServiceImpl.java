package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Promotion;
import com.example.TTLTSEDU.entity.RankCustomer;
import com.example.TTLTSEDU.repository.PromotionRepository;
import com.example.TTLTSEDU.request.PromotionRequest;
import com.example.TTLTSEDU.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAllByIsActive(true);
    }

    @Transactional
    @Scheduled(cron = "0 * * * * ?")
    public void deletePromotionExpiredEndTime() {
        List<Promotion> listPromotionExpired = promotionRepository.findByEndTimeBeforeAndIsActive(new Date(), true);
        if (!listPromotionExpired.isEmpty()) {
            for (Promotion promotion: listPromotionExpired) {
                promotion.setIsActive(false);
                promotionRepository.save(promotion);
            }
        }
    }

    @Override
    public Integer add(PromotionRequest promotionRequest) throws ParseException {
        Promotion promotion = promotionRequest.add(new Promotion());
//        String regex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        if (promotion.getStartTime().compareTo(new Date()) < 0) {
            return 2;
        } else if (promotion.getStartTime().compareTo(promotion.getEndTime()) > 0) {
            return 0;
        }
        promotionRepository.save(promotion);
        return 1;
    }

    @Override
    public Integer update(PromotionRequest promotionRequest, Integer id) throws ParseException {
        Promotion promotion = promotionRepository.findById(id).orElse(null);
        if (promotion != null) {
            if (promotionRequest.getStartTime().compareTo(promotionRequest.getEndTime()) > 0) {
                return 0;
            } else if (promotionRequest.getStartTime().compareTo(String.valueOf(new Date())) < 0) {
                return 2;
            }
            promotion = promotionRequest.update(promotionRequest, id);
            promotionRepository.save(promotion);
            return 1;
        } else {
            return 3;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Promotion promotion = promotionRepository.findById(id).orElse(null);
        if (promotion != null) {
            promotion.setIsActive(false);
            promotionRepository.save(promotion);
            return true;
        } else {
            return false;
        }
    }
}
