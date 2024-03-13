package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    Schedule findTopByOrderByIdDesc();

    @Query(value = "SELECT s FROM Schedule s " +
            "INNER JOIN Movie m ON m.id = s.movie.id " +
            "INNER JOIN Room r ON r.id = s.room.id " +
            "WHERE m.id =:movieId AND r.id =:roomId " +
            "AND s.startAt >= :startAt AND s.endAt <= :endAt")
    List<Schedule> checkTimeSchedule(Date startAt, Date endAt, Integer movieId, Integer roomId);
}
