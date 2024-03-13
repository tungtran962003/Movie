package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.PromotionRequest;
import com.example.TTLTSEDU.service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> add(@RequestBody @Valid PromotionRequest promotionRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            promotionService.add(promotionRequest);
            return ResponseEntity.ok("Added data successfully");
        }
    }
}
