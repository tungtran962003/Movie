package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Promotion;
import com.example.TTLTSEDU.repository.PromotionRepository;
import com.example.TTLTSEDU.request.PromotionRequest;
import com.example.TTLTSEDU.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public void add(PromotionRequest promotionRequest) {
        Promotion promotion = promotionRequest.add(new Promotion());
        promotionRepository.save(promotion);
    }
}
