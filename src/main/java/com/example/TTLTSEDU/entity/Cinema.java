package com.example.TTLTSEDU.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private String address;

    private String description;

    private String code;

    private String nameOfCinema;

    private Boolean isActive;
}
