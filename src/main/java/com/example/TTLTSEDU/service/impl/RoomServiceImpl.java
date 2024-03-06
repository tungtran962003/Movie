package com.example.TTLTSEDU.service.impl;

import com.example.TTLTSEDU.entity.Room;
import com.example.TTLTSEDU.repository.RoomRepository;
import com.example.TTLTSEDU.request.RoomRequest;
import com.example.TTLTSEDU.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAll() {
        return roomRepository.findAllByIsActive(true);
    }


    public String generateCode() {
        Room roomFinalPresent = roomRepository.findTopByOrderByIdDesc();
        if (roomFinalPresent == null) {
            return "RM00001";
        }
        Integer idFinalPresent = roomFinalPresent.getId() + 1;
        String code = String.format("%05d", idFinalPresent);
        return "RM" + code;
    }

    @Override
    public void add(RoomRequest roomRequest) {
        Room room = roomRequest.add(new Room());
        room.setCode(generateCode());
        roomRepository.save(room);
    }

    @Override
    public Boolean update(RoomRequest roomRequest, Integer id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room = roomRequest.update(roomRequest, id, room.getCode());
            roomRepository.save(room);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setIsActive(false);
            roomRepository.save(room);
            return true;
        } else {
            return false;
        }
    }
}
