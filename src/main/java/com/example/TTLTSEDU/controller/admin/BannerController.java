package com.example.TTLTSEDU.controller.admin;

import com.example.TTLTSEDU.request.BannerRequest;
import com.example.TTLTSEDU.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(bannerService.getAll());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<?> add(@ModelAttribute BannerRequest bannerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            bannerService.add(bannerRequest);
            return ResponseEntity.ok("Added data successfully");
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseEntity<?> update(@PathVariable Integer id, @ModelAttribute BannerRequest bannerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseEntity.ok(error);
        } else {
            if (bannerService.update(bannerRequest, id)) {
                return ResponseEntity.ok("Added data successfully");
            } else {
                return ResponseEntity.ok("Data does not exist");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (bannerService.delete(id)) {
            return ResponseEntity.ok("Deleted data successfully");
        } else {
            return ResponseEntity.ok("Data does not exist");
        }
    }
}
