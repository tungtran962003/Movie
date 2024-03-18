package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.dto.TotalRevenueDto;
import com.example.TTLTSEDU.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findAllByIsActive(Boolean isActive);
    Cinema findTopByOrderByIdDesc();

//    @Query(value = "SELECT c.nameOfCinema as cinemaName, " +
//            "SUM(B.totalMoney) as totalMoney " +
//            "FROM Cinema c " +
//            "JOIN Room r ON r.cinema.id = c.id " +
//            "JOIN Schedule s ON s.room.id = r.id " +
//            "JOIN Ticket t ON t.schedule.id = s.id " +
//            "JOIN BillTicket bt ON bt.ticket.id = t.id " +
//            "JOIN Bill b ON b.id = bt.bill.id " +
//            "JOIN BillFood bf ON bf.bill.id = b.id " +
//            "WHERE b.billStatus.id = 1 AND c.isActive = true " +
//            "AND b.updateTime >= :startDate AND b.updateTime <= :endDate " +
//            "GROUP BY c.nameOfCinema")
//    List<TotalRevenueDto> getTotalMoneyByCinema(Date startDate, Date endDate);


    @Query(value = "SELECT c.nameOfCinema as cinemaName, SUM(B.totalMoney) AS totalMoney\n" +
            "FROM Cinema c\n" +
            "JOIN Room r ON r.CinemaId = c.id\n" +
            "JOIN schedule s ON s.RoomId = r.id\n" +
            "JOIN Ticket t ON t.ScheduleId = s.id\n" +
            "JOIN BillTicket bt ON bt.TicketId = t.id\n" +
            "JOIN Bill b ON b.id = bt.BillId\n" +
            "JOIN BillFood bf ON bf.BillId = b.id\n" +
            "WHERE b.BillStatusId = 1 AND c.isActive = 1 \n" +
            "AND b.updateTime >= :startDate AND b.updateTime <= :endDate " +
            "GROUP BY c.nameOfCinema", nativeQuery = true)
    List<TotalRevenueDto> getTotalMoneyByCinema(Date startDate, Date endDate);
}
