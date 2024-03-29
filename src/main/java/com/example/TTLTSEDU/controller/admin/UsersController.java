package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.UsersRequest;
import com.example.TTLTSEDU.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(usersService.getOne(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid UsersRequest usersRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            usersService.add(usersRequest);
            return ResponseEntity.ok("Added data successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UsersRequest usersRequest, BindingResult bindingResult,
                                    @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (usersService.update(usersRequest, id)) {
                return ResponseEntity.ok("Successfully edited data");
            } else {
                return ResponseEntity.ok("Data does not exist");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (usersService.delete(id)) {
            return ResponseEntity.ok("Deleted data successfully");
        } else {
            return ResponseEntity.ok("Data does not exist");
        }
    }
}
