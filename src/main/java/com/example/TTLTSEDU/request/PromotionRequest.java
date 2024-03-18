package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Promotion;
import com.example.TTLTSEDU.entity.RankCustomer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class PromotionRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    @NotNull
    private Integer percents;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String type;

    @NotNull
    private Integer rankCustomerId;

    public Promotion add(Promotion promotion) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        promotion.setName(this.name);
        promotion.setIsActive(true);
        promotion.setDescription(this.description);
        promotion.setPercents(this.percents);
        promotion.setQuantity(this.quantity);
        promotion.setType(this.type);
        promotion.setStartTime(sdf.parse(this.startTime));
        promotion.setEndTime(sdf.parse(this.endTime));
        promotion.setRankCustomer(RankCustomer.builder().id(this.rankCustomerId).build());
        return promotion;
    }

    public Promotion update(PromotionRequest promotionRequest, Integer id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Promotion promotion = new Promotion();
        promotion.setId(id);
        promotion.setName(promotionRequest.getName());
        promotion.setDescription(promotionRequest.getDescription());
        promotion.setPercents(promotionRequest.getPercents());
        promotion.setQuantity(promotionRequest.getQuantity());
        promotion.setType(promotionRequest.getType());
        promotion.setStartTime(sdf.parse(promotionRequest.getStartTime()));
        promotion.setEndTime(sdf.parse(promotionRequest.getEndTime()));
        promotion.setIsActive(true);
        promotion.setRankCustomer(RankCustomer.builder().id(promotionRequest.getRankCustomerId()).build());
        return promotion;
    }
}
