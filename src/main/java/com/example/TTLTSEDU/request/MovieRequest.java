package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Movie;
import com.example.TTLTSEDU.entity.MovieType;
import com.example.TTLTSEDU.entity.Rate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class MovieRequest {

    @NotNull
    private Integer movieDuration;

    private String endTime;

    private String premiereDate;

    @NotBlank
    private String description;

    @NotBlank
    private String director;

    private MultipartFile image;

    private MultipartFile heroImage;

    @NotBlank
    private String language;

    @NotNull
    private Integer movieTypeId;

    @NotBlank
    private String name;

    private Integer rateId;

    @NotBlank
    private String trailer;

    public Movie add(Movie movie) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        movie.setDescription(this.description);
        movie.setName(this.name);
        movie.setMovieDuration(this.movieDuration);
        movie.setDirector(this.director);
        movie.setEndTime(sdf.parse(this.endTime));
        movie.setIsActive(true);
        movie.setLanguage(this.language);
        movie.setPremiereDate(sdf.parse(this.premiereDate));
        movie.setRate(Rate.builder().id(this.rateId).build());
        movie.setTrailer(this.trailer);
        movie.setMovieType(MovieType.builder().id(this.movieTypeId).build());
        return movie;
    }

    public Movie update(MovieRequest movieRequest, Integer id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Movie movie = new Movie();
        movie.setId(id);
        movie.setDescription(movieRequest.getDescription());
        movie.setName(movieRequest.getName());
        movie.setMovieDuration(movieRequest.getMovieDuration());
        movie.setDirector(movieRequest.getDirector());
        movie.setEndTime(sdf.parse(movieRequest.getEndTime()));
        movie.setIsActive(true);
        movie.setLanguage(movieRequest.getLanguage());
        movie.setPremiereDate(sdf.parse(movieRequest.getPremiereDate()));
        movie.setRate(Rate.builder().id(movieRequest.getRateId()).build());
        movie.setTrailer(movieRequest.getTrailer());
        movie.setMovieType(MovieType.builder().id(movieRequest.getMovieTypeId()).build());
        return movie;
    }
}
