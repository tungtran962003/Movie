package com.example.TTLTSEDU.service;

import com.example.TTLTSEDU.entity.Room;
import com.example.TTLTSEDU.request.RoomRequest;

import java.util.List;

public interface RoomService {

    List<Room> getAll();

    void add(RoomRequest roomRequest);

    Boolean update(RoomRequest roomRequest, Integer id);

    Boolean delete(Integer id);
}
