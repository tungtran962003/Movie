package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Rate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateRequest {

    @NotBlank
    private String description;

    public Rate add(Rate rate) {
        rate.setDescription(this.description);
        return rate;
    }

    public Rate update(RateRequest rateRequest, Integer id, String code) {
        Rate rate = new Rate();
        rate.setId(id);
        rate.setCode(code);
        rate.setDescription(rateRequest.getDescription());
        return rate;
    }
}
