package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findTopByOrderByIdDesc();
}
