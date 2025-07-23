package com.example.nat.clone.service;

import com.example.nat.clone.model.dto.RoomDTO;
import com.example.nat.clone.model.entity.Room;

import java.util.List;

public interface RoomService {

    Room createRoom(RoomDTO room);

    Room updateRoom(RoomDTO room);

    RoomDTO getRoomById(String id);

    List<RoomDTO> getAllRoomsAvailable();

    List<RoomDTO> getAllRoomsUnAvailable();

    public void displayAllRoomAvailable();

    public void displayAllRoomUnAvailable();

    public void displayAllRoom();


    String deleteRoom(String id);

}
