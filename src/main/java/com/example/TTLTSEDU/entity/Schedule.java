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
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Double price;

    private Date startAt;

    private Date endAt;

    private String code;

    @ManyToOne
    @JoinColumn(name = "MovieId", referencedColumnName = "Id")
    private Movie movie;

    private String name;

    @ManyToOne
    @JoinColumn(name = "RoomId", referencedColumnName = "Id")
    private Room room;

    private Boolean isActive;
}
