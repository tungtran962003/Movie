package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Rate;
import com.example.TTLTSEDU.request.RateRequest;

import java.util.List;

public interface RateService {

    List<Rate> getAll();

    void add(RateRequest rateRequest);

    Boolean update(RateRequest rateRequest, Integer id);
}
