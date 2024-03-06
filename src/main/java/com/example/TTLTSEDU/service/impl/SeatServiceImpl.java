package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Seat;
import com.example.TTLTSEDU.repository.SeatRepository;
import com.example.TTLTSEDU.request.SeatRequest;
import com.example.TTLTSEDU.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAll() {
        return seatRepository.findAllByIsActive(true);
    }


    @Override
    public void add(SeatRequest seatRequest) {
        Seat seat = seatRequest.add(new Seat());
        seatRepository.save(seat);
    }

    @Override
    public Boolean update(SeatRequest seatRequest, Integer id) {
        Seat seat = seatRepository.findById(id).orElse(null);
        if (seat != null) {
            seat = seatRequest.update(seatRequest, id);
            seatRepository.save(seat);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Seat seat = seatRepository.findById(id).orElse(null);
        if (seat != null) {
            seat.setIsActive(false);
            seatRepository.save(seat);
            return true;
        } else {
            return false;
        }
    }

}
