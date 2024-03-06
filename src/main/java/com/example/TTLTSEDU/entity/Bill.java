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
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Double totalMoney;

    private String tradingCode;

    private Date createTime;

    private Integer customerId;

    private String name;

    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "PromotionId", referencedColumnName = "Id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "BillStatusId", referencedColumnName = "Id")
    private BillStatus billStatus;

    private Boolean isActive;

}
