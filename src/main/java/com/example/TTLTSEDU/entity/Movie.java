package com.example.TTLTSEDU.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Integer movieDuration;

    private Date endTime;

    private Date premiereDate;

    private String description;

    private String director;

    private String image;

    private String heroImage;

    private String language;

    @ManyToOne
    @JoinColumn(name = "MovieTypeId", referencedColumnName = "Id")
    private MovieType movieType;

    private String name;

    @ManyToOne
    @JoinColumn(name = "RateId", referencedColumnName = "Id")
    private Rate rate;

    private String trailer;

    private Boolean isActive;
}
