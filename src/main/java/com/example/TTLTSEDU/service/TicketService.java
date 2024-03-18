package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Ticket;
import com.example.TTLTSEDU.request.TicketRaquest;

import java.util.List;

public interface TicketService {

    List<Ticket> getAll();

    void add(TicketRaquest ticketRaquest);

    Boolean update(TicketRaquest ticketRaquest, Integer id);

    Boolean delete(Integer id);
}
