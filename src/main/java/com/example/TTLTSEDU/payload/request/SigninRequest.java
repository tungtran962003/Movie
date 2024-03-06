package com.example.TTLTSEDU.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SigninRequest {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 50, message = "Username maximum 50 characters")
    private String username;

    @NotBlank
    @Size(min = 6, max = 50)
    private String password;
}
