package com.example.TTLTSEDU.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private Integer id;

    private String username;

    private String email;

//    private String roleName;

    private List<String> roles;

    private String name;

    public JwtResponse(String token, Integer id, String username, String email, List<String> roles, String name) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.name = name;
    }
}
