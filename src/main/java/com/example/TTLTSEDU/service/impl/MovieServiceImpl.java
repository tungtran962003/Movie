package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.dto.MovieFilterDto;
import com.example.TTLTSEDU.dto.MovieResponseDto;
import com.example.TTLTSEDU.dto.impl.MovieResponseDtoImpl;
import com.example.TTLTSEDU.entity.Movie;
import com.example.TTLTSEDU.repository.MovieRepository;
import com.example.TTLTSEDU.request.MovieRequest;
import com.example.TTLTSEDU.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<MovieResponseDto> getFeaturedMovie() {
        return movieRepository.getFeaturedMovie();
    }

    @Override
    public void add(MovieRequest movieRequest) {
        Movie movie = movieRequest.add(new Movie());
        movieRepository.save(movie);
    }

    @Override
    public Boolean update(MovieRequest movieRequest, Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie = movieRequest.update(movieRequest, id);
            movieRepository.save(movie);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setIsActive(false);
            movieRepository.save(movie);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<MovieFilterDto> filterMovieResponse(String movieName) {
        return movieRepository.filterMovieResponse(movieName);
    }

    @Override
    public Movie getOne(Integer id) {
        return null;
    }
}
