package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.dto.FoodDto;
import com.example.TTLTSEDU.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findAllByIsActive(Boolean isActive);

    Food findTopByOrderByIdDesc();

    @Query(value = "SELECT SUM(bf.quantity) as quantityBuy, " +
            "f.nameOfFood as name, " +
            "f.price as price " +
            "FROM BillFood bf\n" +
            "INNER JOIN Food f ON f.id = bf.FoodId\n" +
            "INNER JOIN Bill b ON b.id = bf.BillId\n" +
            "WHERE b.updateTime >= DATEADD(DAY, -7, GETDATE()) AND f.isActive = 1\n" +
            "GROUP BY f.nameOfFood, f.price", nativeQuery = true)
    List<FoodDto> bestSellingFood();
}
