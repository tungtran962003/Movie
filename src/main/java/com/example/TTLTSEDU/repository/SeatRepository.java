package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByIsActive(Boolean isActive);

    Seat findTopByOrderByIdDesc();
}
