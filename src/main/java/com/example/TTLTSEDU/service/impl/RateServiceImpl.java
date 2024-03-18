package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Rate;
import com.example.TTLTSEDU.entity.Room;
import com.example.TTLTSEDU.repository.RateRepository;
import com.example.TTLTSEDU.request.RateRequest;
import com.example.TTLTSEDU.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    public String generateCode() {
        Rate rateFinalPresent = rateRepository.findTopByOrderByIdDesc();
        if (rateFinalPresent == null) {
            return "R00001";
        }
        Integer idFinalPresent = rateFinalPresent.getId() + 1;
        String code = String.format("%05d", idFinalPresent);
        return "R" + code;
    }

    @Override
    public List<Rate> getAll() {
        return rateRepository.findAll();
    }

    @Override
    public void add(RateRequest rateRequest) {
        Rate rate = rateRequest.add(new Rate());
        rate.setCode(generateCode());
        rateRepository.save(rate);
    }

    @Override
    public Boolean update(RateRequest rateRequest, Integer id) {
        Rate rate = rateRepository.findById(id).orElse(null);
        if (rate != null) {
            Rate rateUpdate = rateRequest.update(rateRequest, id, rate.getCode());
            rateRepository.save(rateUpdate);
            return true;
        } else {
            return false;
        }
    }
}
