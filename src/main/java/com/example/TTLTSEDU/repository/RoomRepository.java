package com.example.TTLTSEDU.repository;

import com.example.TTLTSEDU.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findAllByIsActive(Boolean isActive);

    Room findTopByOrderByIdDesc();
}
