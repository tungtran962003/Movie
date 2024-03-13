package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.BillFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillFoodRepository extends JpaRepository<BillFood, Integer> {
}
