package com.example.TTLTSEDU.controller.common;

import com.example.TTLTSEDU.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getFeatured")
    public ResponseEntity<?> getFeaturedMovie() {
        return ResponseEntity.ok(movieService.getFeaturedMovie());
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterMovieResponse(@RequestParam(required = false) String movieName) {
        return ResponseEntity.ok(movieService.filterMovieResponse(movieName));
    }
}
