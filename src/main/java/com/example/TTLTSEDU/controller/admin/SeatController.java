package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.SeatRequest;
import com.example.TTLTSEDU.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(seatService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid SeatRequest seatRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            seatService.add(seatRequest);
            return ResponseEntity.ok("Added data successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid SeatRequest seatRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (seatService.update(seatRequest, id)) {
                return ResponseEntity.ok("Successfully edited data");
            } else {
                return ResponseEntity.ok("Data does not exist");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (seatService.delete(id)) {
            return ResponseEntity.ok("Deleted data successfully");
        } else {
            return ResponseEntity.ok("Data does not exist");
        }
    }
}
