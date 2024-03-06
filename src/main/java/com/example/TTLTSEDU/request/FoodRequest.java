package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Food;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequest {

    private Double price;

    private String description;

    private String image;

    private String nameOfFood;

    public Food add(Food food) {
        food.setImage(this.image);
        food.setNameOfFood(this.nameOfFood);
        food.setDescription(this.description);
        food.setPrice(this.price);
        food.setIsActive(true);
        return food;
    }

    public Food update(FoodRequest foodRequest, Integer id) {
        Food food = new Food();
        food.setId(id);
        food.setImage(foodRequest.getImage());
        food.setNameOfFood(foodRequest.getNameOfFood());
        food.setDescription(foodRequest.getDescription());
        food.setPrice(foodRequest.getPrice());
        food.setIsActive(true);
        return food;
    }
}
