package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Promotion;
import com.example.TTLTSEDU.entity.RankCustomer;

import java.util.Date;

public class PromotionRequest {

    private String name;

    private String description;

    private Date startTime;

    private Date endTime;

    private Integer percents;

    private Integer quantity;

    private String type;

    private Integer rankCustomerId;

    public Promotion add(Promotion promotion) {
        promotion.setName(this.name);
        promotion.setIsActive(true);
        promotion.setDescription(this.description);
        promotion.setPercents(this.percents);
        promotion.setQuantity(this.quantity);
        promotion.setType(this.type);
        promotion.setStartTime(this.startTime);
        promotion.setEndTime(this.endTime);
        promotion.setRankCustomer(RankCustomer.builder().id(rankCustomerId).build());
        return promotion;
    }
}
