package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findAllByIsActive(Boolean isActive);

    Food findTopByOrderByIdDesc();
}
