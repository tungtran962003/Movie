package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.dto.TotalRevenueDto;
import com.example.TTLTSEDU.entity.Cinema;
import com.example.TTLTSEDU.request.CinemaRequest;

import java.text.ParseException;
import java.util.List;

public interface CinemaService {

    List<Cinema> getAll();

    void add(CinemaRequest cinemaRequest);

    Boolean update(CinemaRequest cinemaRequest, Integer id);

    Boolean delete(Integer id);

    Boolean checkDate(String startDate, String endDate);

    List<TotalRevenueDto> getTotalMoneyByCinema(String startDate, String endDate) throws ParseException;

}
