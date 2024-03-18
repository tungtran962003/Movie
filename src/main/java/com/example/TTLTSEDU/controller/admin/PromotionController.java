package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.PromotionRequest;
import com.example.TTLTSEDU.service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Objects;

@RestController
@RequestMapping("/auth/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(promotionService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid PromotionRequest promotionRequest, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            return switch (promotionService.add(promotionRequest)) {
                case 1 -> ResponseEntity.ok("Added data successfully");
                case 2 -> ResponseEntity.ok("Start time must be less than the current date");
                case 4 -> ResponseEntity.ok("Malformed");
                case 0 -> ResponseEntity.ok("Start time must be less than end time");
                default -> null;
            };
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid PromotionRequest promotionRequest, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            switch (promotionService.update(promotionRequest, id)) {
                case 1:
                    return ResponseEntity.ok("Successfully edited data");
                case 2:
                    return ResponseEntity.ok("Start time must be less than the current date");
                case 3:
                    return ResponseEntity.ok("Data does not exist");
                case 0:
                    return ResponseEntity.ok("Start time must be less than end time");
            }
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (promotionService.delete(id)) {
            return ResponseEntity.ok("Deleted data successfully");
        } else {
            return ResponseEntity.ok("Data does not exist");
        }
    }
}
