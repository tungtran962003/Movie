package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatRequest {

    private Integer number;

    private Integer seatStatusId;

    private String line;

    private Integer roomId;

    private Integer seatTypeId;

    public Seat add(Seat seat) {
        seat.setIsActive(true);
        seat.setLine(this.line);
        seat.setNumber(this.number);
        seat.setSeatStatus(SeatStatus.builder().id(this.seatStatusId).build());
        seat.setSeatType(SeatType.builder().id(this.seatTypeId).build());
        seat.setRoom(Room.builder().id(this.roomId).build());
        return seat;
    }

    public Seat update(SeatRequest seatRequest, Integer id) {
        Seat seat = new Seat();
        seat.setId(id);
        seat.setIsActive(true);
        seat.setLine(seatRequest.getLine());
        seat.setNumber(seatRequest.getNumber());
        seat.setSeatStatus(SeatStatus.builder().id(seatRequest.getSeatStatusId()).build());
        seat.setSeatType(SeatType.builder().id(seatRequest.getSeatStatusId()).build());
        seat.setRoom(Room.builder().id(seatRequest.getRoomId()).build());
        return seat;
    }
}
