package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.RankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankCustomerRepository extends JpaRepository<RankCustomer, Integer> {

    List<RankCustomer> findAllByIsActive(Boolean isActive);
}
