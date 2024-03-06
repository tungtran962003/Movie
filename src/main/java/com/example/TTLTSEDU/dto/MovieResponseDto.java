package com.example.TTLTSEDU.dto;

import java.util.Date;

public interface MovieResponseDto {

    Integer getMovieDuration();

    Date getEndTime();

    Date getPremiereDate();

    String getDescription();

    String getDirector();

    String getImage();

    String getHeroImage();

    String getLanguage();

    String getMovieTypeName();

    String getName();

    String getRateCode();

    String getTrailer();

    Integer getQuantityTicket();
}
