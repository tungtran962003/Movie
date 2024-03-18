package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.RankCustomer;
import com.example.TTLTSEDU.entity.Role;
import com.example.TTLTSEDU.entity.UserStatus;
import com.example.TTLTSEDU.entity.Users;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class UsersRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String username;

    private Integer roleId;

    private Integer rankCustomerId;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public Users add(Users users) {
        users.setUserStatus(UserStatus.builder().id(2).build());
        users.setRole(Role.builder().id(this.roleId).build());
        users.setIsActive(true);
        users.setPoint(0);
        users.setUsername(this.username);
        users.setName(this.name);
        users.setPhoneNumber(this.phoneNumber);
        users.setEmail(this.email);
        users.setRankCustomer(RankCustomer.builder().id(this.rankCustomerId).build());
//        users.setPassword(passwordEncoder.encode(this.password));
        return users;
    }

    public Users update(UsersRequest usersRequest, Integer id) {
        Users users = new Users();
        users.setUsername(usersRequest.getUsername());
        users.setName(usersRequest.getName());
        users.setRankCustomer(RankCustomer.builder().id(usersRequest.getRankCustomerId()).build());
        users.setEmail(usersRequest.getEmail());
        users.setRole(Role.builder().id(usersRequest.getRoleId()).build());
        users.setIsActive(true);
        users.setPhoneNumber(usersRequest.getPhoneNumber());
        users.setId(id);
        return users;
    }
}
