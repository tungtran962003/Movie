package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.RankCustomer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankCustomerRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Integer point;

    public RankCustomer add(RankCustomer rankCustomer) {
        rankCustomer.setName(this.name);
        rankCustomer.setPoint(this.point);
        rankCustomer.setIsActive(true);
        rankCustomer.setDescription(this.description);
        return rankCustomer;
    }

    public RankCustomer update(RankCustomerRequest rankCustomerRequest, Integer id) {
        RankCustomer rankCustomer = new RankCustomer();
        rankCustomer.setId(id);
        rankCustomer.setName(rankCustomerRequest.getName());
        rankCustomer.setPoint(rankCustomerRequest.getPoint());
        rankCustomer.setDescription(rankCustomerRequest.getDescription());
        rankCustomer.setIsActive(true);
        return rankCustomer;
    }
}
