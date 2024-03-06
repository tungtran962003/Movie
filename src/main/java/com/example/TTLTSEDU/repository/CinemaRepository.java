package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findAllByIsActive(Boolean isActive);
    Cinema findTopByOrderByIdDesc();
}
