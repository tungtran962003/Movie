package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.RoomRequest;
import com.example.TTLTSEDU.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid RoomRequest roomRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            roomService.add(roomRequest);
            return ResponseEntity.ok("Added data successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid RoomRequest roomRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (roomService.update(roomRequest, id)) {
                return ResponseEntity.ok("Successfully edited data");
            } else {
                return ResponseEntity.ok("Data does not exist");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (roomService.delete(id)) {
            return ResponseEntity.ok("Deleted data successfully");
        } else {
            return ResponseEntity.ok("Data does not exist");
        }
    }
}
