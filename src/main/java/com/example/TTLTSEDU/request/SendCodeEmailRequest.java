package com.example.TTLTSEDU.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendCodeEmailRequest {

    @NotBlank
    private String email;
}
