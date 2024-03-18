package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.dto.MovieFilterDto;
import com.example.TTLTSEDU.dto.MovieResponseDto;
import com.example.TTLTSEDU.dto.impl.MovieResponseDtoImpl;
import com.example.TTLTSEDU.entity.Banner;
import com.example.TTLTSEDU.entity.Movie;
import com.example.TTLTSEDU.repository.MovieRepository;
import com.example.TTLTSEDU.request.MovieRequest;
import com.example.TTLTSEDU.service.MovieService;
import com.example.TTLTSEDU.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<MovieResponseDto> getFeaturedMovie() {
        return movieRepository.getFeaturedMovie();
    }

    @Override
    public void add(MovieRequest movieRequest) throws ParseException {
        String uploadDirImage = "./src/main/resources/static/assets/movie/image";
        String uploadDirHeroImage = "./src/main/resources/static/assets/movie/heroImage";
        String fileNameImage = movieRequest.getImage().getOriginalFilename();
        String fileNameHeroImage = movieRequest.getImage().getOriginalFilename();
        String newFileNameImage = "movieImage_" + new Date().getTime();
        String newFileNameHeroImage = "movieHeroImage_" + new Date().getTime();
        String imagePath = FileUtil.copyFile(movieRequest.getImage(), fileNameImage, uploadDirImage);
        String heroImagePath = FileUtil.copyFile(movieRequest.getImage(), fileNameHeroImage, uploadDirHeroImage);
        String imageUrl = FileUtil.rename(imagePath, newFileNameImage);
        String heroImageUrl = FileUtil.rename(heroImagePath, newFileNameHeroImage);
        Movie movie = movieRequest.add(new Movie());
        movie.setImage(imageUrl);
        movie.setHeroImage(heroImageUrl);
        movieRepository.save(movie);
    }

    @Override
    public Boolean update(MovieRequest movieRequest, Integer id) throws ParseException {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie = movieRequest.update(movieRequest, id);
            String uploadDirImage = "./src/main/resources/static/assets/movie/image";
            String uploadDirHeroImage = "./src/main/resources/static/assets/movie/heroImage";
            String fileNameImage = movieRequest.getImage().getOriginalFilename();
            String fileNameHeroImage = movieRequest.getImage().getOriginalFilename();
            String newFileNameImage = "movieImage_" + new Date().getTime();
            String newFileNameHeroImage = "movieHeroImage_" + new Date().getTime();
            String imagePath = FileUtil.copyFile(movieRequest.getImage(), fileNameImage, uploadDirImage);
            String heroImagePath = FileUtil.copyFile(movieRequest.getImage(), fileNameHeroImage, uploadDirHeroImage);
            String imageUrl = FileUtil.rename(imagePath, newFileNameImage);
            String heroImageUrl = FileUtil.rename(heroImagePath, newFileNameHeroImage);
            movie.setImage(imageUrl);
            movie.setHeroImage(heroImageUrl);
            movieRepository.save(movie);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setIsActive(false);
            movieRepository.save(movie);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<MovieFilterDto> filterMovieResponse(String movieName) {
        return movieRepository.filterMovieResponse(movieName);
    }

    @Override
    public Movie getOne(Integer id) {
        return null;
    }
}
