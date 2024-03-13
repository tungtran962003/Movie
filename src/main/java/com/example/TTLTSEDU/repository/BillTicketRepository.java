package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.BillTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTicketRepository extends JpaRepository<BillTicket, Integer> {
}
