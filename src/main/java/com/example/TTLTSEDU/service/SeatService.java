package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Seat;
import com.example.TTLTSEDU.request.SeatRequest;

import java.util.List;

public interface SeatService {

    List<Seat> getAll();

    void add(SeatRequest seatRequest);

    Boolean update(SeatRequest seatRequest, Integer id);

    Boolean delete(Integer id);
}
