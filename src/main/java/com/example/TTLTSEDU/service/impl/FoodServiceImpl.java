package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Food;
import com.example.TTLTSEDU.repository.FoodRepository;
import com.example.TTLTSEDU.request.FoodRequest;
import com.example.TTLTSEDU.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAll() {
        return foodRepository.findAllByIsActive(true);
    }

    @Override
    public void add(FoodRequest foodRequest) {
        Food food = foodRequest.add(new Food());
        foodRepository.save(food);
    }

    @Override
    public Boolean update(FoodRequest foodRequest, Integer id) {
        Food food = foodRepository.findById(id).orElse(null);
        if (food != null) {
            food = foodRequest.update(foodRequest, id);
            foodRepository.save(food);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Food food = foodRepository.findById(id).orElse(null);
        if (food != null) {
            food.setIsActive(false);
            foodRepository.save(food);
            return true;
        } else {
            return false;
        }
    }
}
