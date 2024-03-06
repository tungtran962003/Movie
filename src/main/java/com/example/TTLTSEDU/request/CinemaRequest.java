package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Cinema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaRequest {

    @NotBlank
    private String address;

    @NotBlank
    private String description;

    @NotBlank
    private String nameOfCinema;


    public Cinema add(Cinema cinema) {
        cinema.setIsActive(true);
        cinema.setNameOfCinema(this.getNameOfCinema());
        cinema.setAddress(this.getAddress());
        cinema.setDescription(this.getDescription());
        return cinema;
    }

    public Cinema update(CinemaRequest cinemaRequest, Integer id, String code) {
        Cinema cinema = new Cinema();
        cinema.setId(id);
        cinema.setCode(code);
        cinema.setIsActive(true);
        cinema.setNameOfCinema(cinemaRequest.getNameOfCinema());
        cinema.setAddress(cinemaRequest.getAddress());
        cinema.setDescription(cinemaRequest.getDescription());
        return cinema;
    }
}
