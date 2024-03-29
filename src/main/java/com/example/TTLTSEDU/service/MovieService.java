package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.dto.MovieFilterDto;
import com.example.TTLTSEDU.dto.MovieResponseDto;
import com.example.TTLTSEDU.dto.impl.MovieResponseDtoImpl;
import com.example.TTLTSEDU.entity.Movie;
import com.example.TTLTSEDU.request.MovieRequest;

import java.text.ParseException;
import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<MovieResponseDto> getFeaturedMovie();

    void add(MovieRequest movieRequest) throws ParseException;

    Boolean update(MovieRequest movieRequest, Integer id) throws ParseException;

    Boolean delete(Integer id);

    List<MovieFilterDto> filterMovieResponse(String movieName);

    Movie getOne(Integer id);
}
