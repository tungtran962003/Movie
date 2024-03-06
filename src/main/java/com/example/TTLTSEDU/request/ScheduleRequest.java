package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Movie;
import com.example.TTLTSEDU.entity.Room;
import com.example.TTLTSEDU.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ScheduleRequest {

    @NotBlank
    private String startAt;

    @NotBlank
    private String endAt;

    @NotBlank
    private String name;


    private Double price;

    private Integer movieId;

    private Integer roomId;

    public Schedule add(Schedule schedule) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        schedule.setName(this.name);
        schedule.setStartAt(new Date(String.valueOf(sdf.parse(this.startAt))));
        schedule.setEndAt(new Date(String.valueOf(sdf.parse(this.endAt))));
        schedule.setIsActive(true);
        schedule.setRoom(Room.builder().id(this.roomId).build());
        schedule.setMovie(Movie.builder().id(this.movieId).build());
        return schedule;
    }

    public Schedule update(ScheduleRequest scheduleRequest, Integer id, String code) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Schedule schedule = new Schedule();
        schedule.setId(id);
        schedule.setCode(code);
        schedule.setName(scheduleRequest.getName());
        schedule.setStartAt(new Date(String.valueOf(sdf.parse(scheduleRequest.getStartAt()))));
        schedule.setEndAt(new Date(String.valueOf(sdf.parse(scheduleRequest.getEndAt()))));
        schedule.setIsActive(true);
        schedule.setRoom(Room.builder().id(scheduleRequest.getRoomId()).build());
        schedule.setMovie(Movie.builder().id(scheduleRequest.getMovieId()).build());
        return schedule;
    }

}
