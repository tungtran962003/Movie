package com.example.TTLTSEDU.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Users", uniqueConstraints = {
        // 2 trường trong bảng không được trùng lặp dữ liệu
        @UniqueConstraint(columnNames = "Username"),
        @UniqueConstraint(columnNames = "Email"),
})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    private Integer point;

    @NotBlank
    @Size(min = 4, max = 50)
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email
    @Size(max = 100, message = "Email maximum 50 characters")
    private String email;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @Column(length = 255)
//    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name = "RankCustomerId", referencedColumnName = "Id")
    private RankCustomer rankCustomer;

    @ManyToOne
    @JoinColumn(name = "UserStatusId", referencedColumnName = "Id")
    private UserStatus userStatus;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    private Role role;

    public Users(String name, String username, String email, String phoneNumber, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
