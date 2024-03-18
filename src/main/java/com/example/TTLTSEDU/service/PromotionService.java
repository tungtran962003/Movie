package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Promotion;
import com.example.TTLTSEDU.request.PromotionRequest;

import java.text.ParseException;
import java.util.List;

public interface PromotionService {

    List<Promotion> getAll();

    Integer add(PromotionRequest promotionRequest) throws ParseException;

    Integer update(PromotionRequest promotionRequest, Integer id) throws ParseException;

    Boolean delete(Integer id);;
}
