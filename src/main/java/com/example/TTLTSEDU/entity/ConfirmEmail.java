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
@Table(name = "ConfirmEmail")
public class ConfirmEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UsersId", referencedColumnName = "Id")
    private Users users;

    private Date requiredDateTime;

    private Date expiredDateTime;

    private String confirmCode;

    private Boolean isConfirm;
}
