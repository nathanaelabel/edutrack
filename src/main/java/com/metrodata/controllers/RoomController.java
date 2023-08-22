package com.metrodata.controllers;

import com.metrodata.entities.Room;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.RoomData;
import com.metrodata.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("room")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRoom() {
        return roomService.getAllRooms();
    }

    @GetMapping("{id}")
    public Room getRoomById(Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public ResponseData<Room> insertRoom(RoomData roomData) {
        return roomService.insertRoom(roomData);
    }

    @PostMapping("{id}")
    public Room updateRoom(long id, Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("{id}")
    public Room deleteRoom(long id) {
        return roomService.deleteRoom(id);
    }
}
