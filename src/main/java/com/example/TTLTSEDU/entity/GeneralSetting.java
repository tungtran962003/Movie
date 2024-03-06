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
@Table(name = "GeneralSetting")
public class GeneralSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Integer businessHours;

    private Date closeTime;

    private Double fixedTicketPrice;

    private Integer percentDay;

    private Integer percentWeekend;

    private Date timeBeginToChange;
}
