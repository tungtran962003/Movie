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
@Table(name = "Promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Percents")
    private Integer percents;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Type")
    private String type;

    @Column(name = "StartTime")
    private Date startTime;

    @Column(name = "EndTime")
    private Date endTime;

    @Column(name = "Description")
    private String description;

    @Column(name = "Name")
    private String name;

    @Column(name = "IsActive")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "RankCustomerId", referencedColumnName = "Id")
    private RankCustomer rankCustomer;
}
