package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Movie;
import com.example.TTLTSEDU.entity.MovieType;
import com.example.TTLTSEDU.entity.Rate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieRequest {

    private Integer movieDuration;

    private Date endTime;

    private Date premiereDate;

    private String description;

    private String director;

    private String image;

    private String heroImage;

    private String language;

    private Integer movieTypeId;

    private String name;

    private Integer rateId;

    private String trailer;

    public Movie add(Movie movie) {
        movie.setDescription(this.description);
        movie.setImage(this.image);
        movie.setName(this.name);
        movie.setMovieDuration(this.movieDuration);
        movie.setDirector(this.director);
        movie.setEndTime(this.endTime);
        movie.setHeroImage(this.heroImage);
        movie.setIsActive(true);
        movie.setLanguage(this.language);
        movie.setPremiereDate(this.premiereDate);
        movie.setRate(Rate.builder().id(this.rateId).build());
        movie.setTrailer(this.trailer);
        movie.setMovieType(MovieType.builder().id(this.movieTypeId).build());
        return movie;
    }

    public Movie update(MovieRequest movieRequest, Integer id) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setDescription(movieRequest.getDescription());
        movie.setImage(movieRequest.getImage());
        movie.setName(movieRequest.getName());
        movie.setMovieDuration(movieRequest.getMovieDuration());
        movie.setDirector(movieRequest.getDirector());
        movie.setEndTime(movieRequest.getEndTime());
        movie.setHeroImage(movieRequest.getHeroImage());
        movie.setIsActive(true);
        movie.setLanguage(movieRequest.getLanguage());
        movie.setPremiereDate(movieRequest.getPremiereDate());
        movie.setRate(Rate.builder().id(movieRequest.getRateId()).build());
        movie.setTrailer(movieRequest.getTrailer());
        movie.setMovieType(MovieType.builder().id(movieRequest.getMovieTypeId()).build());
        return movie;
    }
}
