package com.metrodata.services;

import com.metrodata.entities.Room;
import com.metrodata.entities.models.ResponseData;
import com.metrodata.entities.models.RoomData;
import com.metrodata.repositories.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private EventService eventService;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room with ID: " + id + " not found"));
    }

    public ResponseData<Room> insertRoom(RoomData roomData) {
        try {
            Room room = new Room();
            room.setName(roomData.getName());
            room.setEvent(eventService.getEventById(roomData.getEventId()));
            Room newRoom = roomRepository.save(room);
            return new ResponseData<>(newRoom, "Room created successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Room updateRoom(long id, Room roomData) {
        Room room = getRoomById(id);
        room.setName(roomData.getName());
        room.setEvent(eventService.getEventById(roomData.getEvent().getId()));
        return roomRepository.save(room);
    }

    public Room deleteRoom(long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
        return room;
    }
}
