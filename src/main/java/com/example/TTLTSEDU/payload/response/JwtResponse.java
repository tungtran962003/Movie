package com.example.TTLTSEDU.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private Integer id;

    private String username;

    private String email;

    private String roleName;


    private String name;

    public JwtResponse(String token, Integer id, String username, String email, String roleName, String name) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roleName = roleName;
        this.name = name;
    }
}
