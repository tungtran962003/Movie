package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    List<Promotion> findAllByIsActive(Boolean isActive);

    List<Promotion> findByEndTimeBeforeAndIsActive(Date endTime, Boolean isActive);
}
