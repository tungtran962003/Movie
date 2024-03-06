package com.example.TTLTSEDU.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "ScheduleId", referencedColumnName = "Id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "SeatId", referencedColumnName = "Id")
    private Seat seat;

    private Double priceTicket;

    private Boolean isActive;
}
