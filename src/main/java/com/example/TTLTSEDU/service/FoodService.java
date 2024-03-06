package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Food;
import com.example.TTLTSEDU.request.FoodRequest;

import java.util.List;

public interface FoodService {

    List<Food> getAll();

    void add(FoodRequest foodRequest);

    Boolean update(FoodRequest foodRequest, Integer id);

    Boolean delete(Integer id);
}
