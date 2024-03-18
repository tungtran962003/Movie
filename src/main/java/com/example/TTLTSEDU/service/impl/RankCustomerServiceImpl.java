package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.RankCustomer;
import com.example.TTLTSEDU.entity.Ticket;
import com.example.TTLTSEDU.repository.RankCustomerRepository;
import com.example.TTLTSEDU.request.RankCustomerRequest;
import com.example.TTLTSEDU.service.RankCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankCustomerServiceImpl implements RankCustomerService {

    @Autowired
    private RankCustomerRepository rankCustomerRepository;

    @Override
    public List<RankCustomer> getAll() {
        return rankCustomerRepository.findAllByIsActive(true);
    }

    @Override
    public void add(RankCustomerRequest rankCustomerRequest) {
        RankCustomer rankCustomer = rankCustomerRequest.add(new RankCustomer());
        rankCustomerRepository.save(rankCustomer);
    }

    @Override
    public Boolean update(RankCustomerRequest rankCustomerRequest, Integer id) {
        RankCustomer rankCustomer = rankCustomerRepository.findById(id).orElse(null);
        if (rankCustomer != null) {
            rankCustomer = rankCustomerRequest.update(rankCustomerRequest, id);
            rankCustomerRepository.save(rankCustomer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        RankCustomer rankCustomer = rankCustomerRepository.findById(id).orElse(null);
        if (rankCustomer != null) {
            rankCustomer.setIsActive(false);
            rankCustomerRepository.save(rankCustomer);
            return true;
        } else {
            return false;
        }
    }
}
