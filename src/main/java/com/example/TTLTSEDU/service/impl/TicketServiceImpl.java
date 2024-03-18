package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Ticket;
import com.example.TTLTSEDU.repository.TicketRepository;
import com.example.TTLTSEDU.request.TicketRaquest;
import com.example.TTLTSEDU.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public String generateCode() {
        Ticket ticketFinalPresent = ticketRepository.findTopByOrderByIdDesc();
        if (ticketFinalPresent == null) {
            return "T00001";
        }
        Integer idFinalPresent = ticketFinalPresent.getId() + 1;
        String code = String.format("%05d", idFinalPresent);
        return "T" + code;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void add(TicketRaquest ticketRaquest) {
        Ticket ticket = ticketRaquest.add(new Ticket());
        ticket.setCode(generateCode());
        ticketRepository.save(ticket);
    }

    @Override
    public Boolean update(TicketRaquest ticketRaquest, Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            Ticket rateUpdate = ticketRaquest.update(ticketRaquest, id, ticket.getCode());
            ticketRepository.save(rateUpdate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket != null) {
            ticket.setIsActive(false);
            ticketRepository.save(ticket);
            return true;
        } else {
            return false;
        }
    }
}
