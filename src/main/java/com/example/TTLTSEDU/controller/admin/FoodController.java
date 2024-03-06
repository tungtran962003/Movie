package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.FoodRequest;
import com.example.TTLTSEDU.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(foodService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid FoodRequest foodRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            foodService.add(foodRequest);
            return ResponseEntity.ok("Added data successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid FoodRequest foodRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (foodService.update(foodRequest, id)) {
                return ResponseEntity.ok("Successfully edited data");
            } else {
                return ResponseEntity.ok("Data does not exist");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (foodService.delete(id)) {
            return ResponseEntity.ok("Deleted data successfully");
        } else {
            return ResponseEntity.ok("Data does not exist");
        }
    }
}
