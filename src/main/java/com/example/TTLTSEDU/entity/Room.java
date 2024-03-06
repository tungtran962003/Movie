package com.example.TTLTSEDU.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Integer capacity;

    private Integer type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "CinemaId", referencedColumnName = "Id")
    private Cinema cinema;

    private String code;

    private String name;

    private Boolean isActive;
}
