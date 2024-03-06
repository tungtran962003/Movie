package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Cinema;
import com.example.TTLTSEDU.request.CinemaRequest;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAll();

    void add(CinemaRequest cinemaRequest);

    Boolean update(CinemaRequest cinemaRequest, Integer id);

    Boolean delete(Integer id);
}
