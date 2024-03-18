package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.RankCustomer;
import com.example.TTLTSEDU.request.RankCustomerRequest;

import java.util.List;

public interface RankCustomerService {

    List<RankCustomer> getAll();

    void add(RankCustomerRequest rankCustomerRequest);

    Boolean update(RankCustomerRequest rankCustomerRequest, Integer id);

    Boolean delete(Integer id);
}
