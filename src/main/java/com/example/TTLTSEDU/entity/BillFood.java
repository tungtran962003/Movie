package com.example.TTLTSEDU.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BillFood")
public class BillFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "BillId", referencedColumnName = "Id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "FoodId", referencedColumnName = "Id")
    private Food food;
}
