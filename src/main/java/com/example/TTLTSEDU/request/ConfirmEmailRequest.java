package com.example.TTLTSEDU.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmEmailRequest {

    private String email;

    private String code;
}
