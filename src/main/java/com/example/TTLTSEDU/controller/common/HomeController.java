package com.example.TTLTSEDU.controller.common;

import com.example.TTLTSEDU.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/detailMovie/{id}")
//    public ResponseEntity<?> getDetailMovie(@PathVariable Integer id) {
//
//        return ResponseEntity.ok();
//    }
}
