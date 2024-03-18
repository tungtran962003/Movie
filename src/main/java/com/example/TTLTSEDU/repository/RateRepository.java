package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

    Rate findTopByOrderByIdDesc();
}
