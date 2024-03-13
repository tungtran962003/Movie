package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Promotion;
import com.example.TTLTSEDU.request.PromotionRequest;

import java.util.List;

public interface PromotionService {

    List<Promotion> getAll();

    void add(PromotionRequest promotionRequest);
}
