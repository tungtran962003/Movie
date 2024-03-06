package com.example.TTLTSEDU.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Integer number;

    @ManyToOne
    @JoinColumn(name = "SeatStatusId", referencedColumnName = "Id")
    private SeatStatus seatStatus;

    private String line;

    @ManyToOne
    @JoinColumn(name = "RoomId", referencedColumnName = "Id")
    private Room room;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "SeatTypeId", referencedColumnName = "Id")
    private SeatType seatType;
}
