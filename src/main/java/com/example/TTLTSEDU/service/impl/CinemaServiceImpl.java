package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Cinema;
import com.example.TTLTSEDU.repository.CinemaRepository;
import com.example.TTLTSEDU.request.CinemaRequest;
import com.example.TTLTSEDU.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAll() {
        return cinemaRepository.findAllByIsActive(true);
    }


    public String generateCode() {
        Cinema cinemaFinalPresent = cinemaRepository.findTopByOrderByIdDesc();
        if (cinemaFinalPresent == null) {
            return "C00001";
        }
        Integer idFinalPresent = cinemaFinalPresent.getId() + 1;
        String code = String.format("%05d", idFinalPresent);
        return "C" + code;
    }

    @Override
    public void add(CinemaRequest cinemaRequest) {
        Cinema cinema = cinemaRequest.add(new Cinema());
        cinema.setCode(generateCode());
        cinemaRepository.save(cinema);
    }

    @Override
    public Boolean update(CinemaRequest cinemaRequest, Integer id) {
        Cinema cinema = cinemaRepository.findById(id).orElse(null);
        if (cinema != null) {
            cinema = cinemaRequest.update(cinemaRequest, id, cinema.getCode());
            cinemaRepository.save(cinema);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Cinema cinema = cinemaRepository.findById(id).orElse(null);
        if (cinema != null) {
            cinema.setIsActive(false);
            cinemaRepository.save(cinema);
            return true;
        } else {
            return false;
        }
    }
}
