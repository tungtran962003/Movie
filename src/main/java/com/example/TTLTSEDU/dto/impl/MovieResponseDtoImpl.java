package com.example.TTLTSEDU.dto.impl;

import com.example.TTLTSEDU.dto.MovieResponseDto;
import com.example.TTLTSEDU.entity.MovieType;
import com.example.TTLTSEDU.entity.Rate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieResponseDtoImpl {

    private Integer movieDuration;

    private Date endTime;

    private Date premiereDate;

    private String description;

    private String director;

    private String image;

    private String heroImage;

    private String language;

    private String movieTypeName;

    private String name;

    private String rateCode;

    private String trailer;

    public static MovieResponseDtoImpl toData(MovieResponseDto dto) {
        MovieResponseDtoImpl movieResponseDto = new MovieResponseDtoImpl();
        movieResponseDto.setMovieDuration(dto.getMovieDuration());
        movieResponseDto.setEndTime(dto.getEndTime());
        movieResponseDto.setPremiereDate(dto.getPremiereDate());
        movieResponseDto.setDescription(dto.getDescription());
        movieResponseDto.setDirector(dto.getDirector());
        movieResponseDto.setImage(dto.getImage());
        movieResponseDto.setHeroImage(dto.getHeroImage());
        movieResponseDto.setLanguage(dto.getLanguage());
        movieResponseDto.setMovieTypeName(dto.getMovieTypeName());
        movieResponseDto.setName(dto.getName());
        movieResponseDto.setRateCode(dto.getRateCode());
        movieResponseDto.setTrailer(dto.getTrailer());
        return movieResponseDto;
    }
}
