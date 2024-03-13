package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    Bill findTopByOrderByIdDesc();

    @Query(value = "SELECT b FROM Bill b " +
            "WHERE b.customerId =:customerId AND b.billStatus.id = 2 AND b.isActive = true " +
            "ORDER BY b.id DESC")
    List<Bill> findTopByCustomerIdAAndBillStatusOrderByIdDesc(Integer customerId);
}
