package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.CinemaRequest;
import com.example.TTLTSEDU.service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;


    @GetMapping("/all")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(cinemaService.getAll());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid CinemaRequest cinemaRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            cinemaService.add(cinemaRequest);
            return ResponseEntity.ok("Added data successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid CinemaRequest cinemaRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (cinemaService.update(cinemaRequest, id)) {
                return ResponseEntity.ok("Successfully edited data");
            } else {
                return ResponseEntity.ok("Data does not exist");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (cinemaService.delete(id)) {
            return ResponseEntity.ok("Deleted data successfully");
        } else {
            return ResponseEntity.ok("Data does not exist");
        }
    }
}
