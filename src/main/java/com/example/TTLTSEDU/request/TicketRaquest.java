package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Schedule;
import com.example.TTLTSEDU.entity.Seat;
import com.example.TTLTSEDU.entity.Ticket;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRaquest {

    @NotNull
    private Double priceTicket;

    @NotNull
    private Integer scheduleId;

    @NotNull
    private Integer seatId;

    public Ticket add(Ticket ticket) {
        ticket.setPriceTicket(this.priceTicket);
        ticket.setIsActive(true);
        ticket.setSchedule(Schedule.builder().id(this.scheduleId).build());
        ticket.setSeat(Seat.builder().id(this.seatId).build());
        return ticket;
    }

    public Ticket update(TicketRaquest ticketRaquest,Integer id, String code) {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setCode(code);
        ticket.setPriceTicket(ticketRaquest.getPriceTicket());
        ticket.setIsActive(true);
        ticket.setSchedule(Schedule.builder().id(ticketRaquest.getScheduleId()).build());
        ticket.setSeat(Seat.builder().id(ticketRaquest.getSeatId()).build());
        return ticket;
    }
}
