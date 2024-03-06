package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.dto.MovieFilterDto;
import com.example.TTLTSEDU.dto.MovieResponseDto;
import com.example.TTLTSEDU.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT m.movieDuration as movieDuration, " +
            "m.endTime as endTime, " +
            "m.premiereDate as premiereDate, " +
            "m.description as description, " +
            "m.director as director, " +
            "m.image as image, " +
            "m.heroImage as heroImage, " +
            "m.language as language, " +
            "m.movieType.movieTypeName as movieTypeName, " +
            "m.name as name, " +
            "m.rate.code as rateCode, " +
            "m.trailer as trailer, " +
            "bt.quantity as quantityTicket " +
            "FROM Movie m \n" +
            "INNER JOIN Schedule s ON s.movie.id = m.id\n" +
            "INNER JOIN Ticket t ON t.schedule.id = s.id\n" +
            "INNER JOIN BillTicket bt ON bt.ticket.id = t.id\n" +
            "WHERE m.isActive = true\n" +
            "ORDER BY bt.quantity DESC")
    List<MovieResponseDto> getFeaturedMovie();


//    @Query(value = "SELECT m.movieDuration as movieDuration, " +
//            "m.endTime as endTime, " +
//            "m.premiereDate as premiereDate, " +
//            "m.description as description, " +
//            "m.director as director, " +
//            "m.image as image, " +
//            "m.heroImage as heroImage, " +
//            "m.language as language, " +
//            "m.movieType.movieTypeName as movieTypeName, " +
//            "m.name as name, " +
//            "m.rate.code as rateCode, " +
//            "m.trailer as trailer " +
//            "FROM Movie m \n" +
//            "INNER JOIN Schedule sch ON sch.movie.id = m.id\n" +
//            "INNER JOIN Room r ON r.id = sch.room.id\n" +
//            "INNER JOIN Cinema c ON c.id = r.cinema.id\n" +
//            "INNER JOIN Seat s ON s.room.id = r.id\n" +
//            "WHERE (c.id IN :listCinemaId OR :listCinemaId IS NULL) " +
//            "AND (r.id IN :listRoomId OR :listRoomId IS NULL) " +
//            "AND (s.id IN :listSeatId OR :listSeatId IS NULL) " +
//            "AND m.isActive = true AND sch.isActive = true AND r.isActive = true AND c.isActive = true\n")
//    List<MovieResponseDto> filterMovieResponse(List<Integer> listCinemaId, List<Integer> listRoomId, List<Integer> listSeatId);


//    @Query(value = "SELECT m.movieDuration as movieDuration, " +
//            "m.endTime as endTime, " +
//            "m.premiereDate as premiereDate, " +
//            "m.description as description, " +
//            "m.director as director, " +
//            "m.image as image, " +
//            "m.heroImage as heroImage, " +
//            "m.language as language, " +
//            "m.name as name, " +
//            "m.trailer as trailer " +
//            "FROM Movie m \n" +
//            "INNER JOIN Schedule sch ON sch.movieid = m.id\n" +
//            "INNER JOIN Room r ON r.id = sch.roomid\n" +
//            "INNER JOIN Cinema c ON c.id = r.cinemaid\n" +
//            "INNER JOIN Seat s ON s.roomid = r.id\n" +
//            "INNER JOIN SeatStatus ss ON ss.id = s.seatStatusId\n" +
//            "WHERE (c.id IN :listCinemaId OR :listCinemaId IS NULL) " +
//            "AND (r.id IN :listRoomId OR :listRoomId IS NULL) " +
//            "AND (ss.id IN :listSeatStatusId OR :listSeatStatusId IS NULL) " +
//            "AND m.isActive = 1 AND sch.isActive = 1 AND r.isActive = 1 AND c.isActive = 1\n", nativeQuery = true)
//    List<MovieResponseDto> filterMovieResponse(String movieName);

    @Query(value = "SELECT m.name as movieName, " +
            "c.nameOfCinema as cinemaName, " +
            "c.address as cinemaAddress, " +
            "rm.name as roomName, " +
            "rm.capacity as capacity, " +
            "s.line as seatLine, " +
            "s.number as seatNumber, " +
            "ss.nameStatus as seatStatusName " +
            "FROM Cinema c \n" +
            "INNER JOIN Room rm ON rm.cinema.id = c.id " +
            "INNER JOIN Seat s ON s.room.id = rm.id " +
            "INNER JOIN Schedule sch ON sch.room.id = rm.id " +
            "INNER JOIN Movie m ON m.id = sch.movie.id " +
            "INNER JOIN SeatStatus ss ON ss.id = s.seatStatus.id " +
            "WHERE m.name LIKE %:movieNameSearch% " +
            "AND m.isActive = true AND sch.isActive = true AND rm.isActive = true AND c.isActive = true\n")
    List<MovieFilterDto> filterMovieResponse(String movieNameSearch);
}
