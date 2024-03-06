package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
