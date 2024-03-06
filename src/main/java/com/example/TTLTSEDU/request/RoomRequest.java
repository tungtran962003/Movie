package com.example.TTLTSEDU.request;

import com.example.TTLTSEDU.entity.Cinema;
import com.example.TTLTSEDU.entity.Room;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {

    private Integer capacity;

    private Integer type;

    @NotBlank
    private String description;

    @NotBlank
    private String name;

    private Integer cinemaId;


    public Room add(Room room) {
        room.setDescription(this.description);
        room.setIsActive(true);
        room.setCapacity(this.capacity);
        room.setName(this.name);
        room.setType(this.type);
        room.setCinema(Cinema.builder().id(this.cinemaId).build());
        return room;
    }

    public Room update(RoomRequest roomRequest, Integer id, String code) {
        Room room = new Room();
        room.setId(id);
        room.setCode(code);
        room.setDescription(roomRequest.getDescription());
        room.setName(roomRequest.getName());
        room.setType(roomRequest.getType());
        room.setCapacity(roomRequest.getCapacity());
        room.setIsActive(true);
        room.setCinema(Cinema.builder().id(roomRequest.getCinemaId()).build());
        return room;
    }
}
