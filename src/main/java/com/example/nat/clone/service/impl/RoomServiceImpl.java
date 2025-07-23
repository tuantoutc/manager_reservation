package com.example.nat.clone.service.impl;


import com.example.nat.clone.exception.AppException;
import com.example.nat.clone.exception.ErrorCode;
import com.example.nat.clone.model.dto.RoomDTO;
import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.model.entity.Room;
import com.example.nat.clone.model.entity.RoomType;
import com.example.nat.clone.repository.HotelRepository;
import com.example.nat.clone.repository.RoomRepository;
import com.example.nat.clone.repository.RoomTypeRepository;
import com.example.nat.clone.service.HotelService;
import com.example.nat.clone.service.RoomService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String deleteRoom(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        roomRepository.delete(room);
        return "Room with ID " + id + " has been deleted successfully.";
    }

    @Override
    public Room createRoom(RoomDTO room) {
        Room newRoom = null;
        if(room.getId() == null || room.getId().isEmpty()) {
            Hotel hotel = hotelRepository.findById(room.getHotelId())
                    .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
            RoomType roomType =  roomTypeRepository.findById(room.getRoomTypeId())
                    .orElseThrow(() -> new IllegalArgumentException("Room type not found"));

            newRoom = new Room().builder()
                    .name(room.getName())
                    .hotel(hotel)
                    .roomType(roomType)
                    .price(room.getPrice())
                    .status(room.getStatus())
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .build();
        }

            return roomRepository.save(newRoom);
    }

    @Override
    public Room updateRoom(RoomDTO room) {
        if (room.getId() == null || room.getId().isEmpty()) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        Room roomEntity = roomRepository.findById(room.getId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        roomEntity = modelMapper.map(room, Room.class);
        roomEntity.setUpdatedAt(LocalDate.now());
        if(room.getHotelId()== null || room.getHotelId().isEmpty()
        || room.getRoomTypeId() == null || room.getRoomTypeId().isEmpty()) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        else {
            Hotel hotel = hotelRepository.findById(room.getHotelId())
                    .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));
            roomEntity.setHotel(hotel);

            RoomType roomType = roomTypeRepository.findById(room.getRoomTypeId())
                    .orElseThrow(() -> new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND));
            roomEntity.setRoomType(roomType);
        }

        return roomRepository.save(roomEntity);
    }

    @Override
    public RoomDTO getRoomById(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        roomDTO.setRoomTypeId(room.getRoomType().getId());
        roomDTO.setHotelId(room.getHotel().getId());

        return roomDTO ;
    }

    @Override
    public List<RoomDTO> getAllRoomsAvailable() {
        List<Room> rooms = roomRepository.findByStatus("available");
        if (rooms != null && !rooms.isEmpty()) {
            return rooms.stream()
                    .map(room -> {
                        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
                        roomDTO.setRoomTypeId(room.getRoomType().getId());
                        roomDTO.setHotelId(room.getHotel().getId());
                        return roomDTO;
                    })
                    .toList();
        }
        return List.of();

    }
    @Override
    public List<RoomDTO> getAllRoomsUnAvailable() {
        List<Room> rooms = roomRepository.findByStatusNot("available");
        if (rooms != null && !rooms.isEmpty()) {
            return rooms.stream()
                    .map(room -> {
                        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
                        roomDTO.setRoomTypeId(room.getRoomType().getId());
                        roomDTO.setHotelId(room.getHotel().getId());
                        return roomDTO;
                    })
                    .toList();
        }
        return List.of();

    }
    @Override
    public void displayAllRoomAvailable() {
        List<RoomDTO> rooms = getAllRoomsAvailable();
        System.out.println("\n=== DANH SÁCH ROOM TRỐNG ===");
        System.out.println("ID --------------------- Name ----------------- Price ----------------- Status ----------------- RoomTypeId ----------------- HotelId");
        rooms.forEach(room -> {
            System.out.printf("%s - %s - %s - %s - %s - %s%n",
                    room.getId(),
                    room.getName(),
                    room.getPrice(),
                    room.getStatus(),
                    room.getRoomTypeId(),
                    room.getHotelId());
        });

    }
    @Override
    public void displayAllRoomUnAvailable() {
        List<RoomDTO> rooms = getAllRoomsUnAvailable();
        System.out.println("\n=== DANH SÁCH ROOM ĐÃ THUÊ ===");
        System.out.println("ID --------------------- Name ----------------- Price ----------------- Status ----------------- RoomTypeId ----------------- HotelId");
        rooms.forEach(room -> {
            System.out.printf("%s - %s - %s - %s - %s - %s%n",
                    room.getId(),
                    room.getName(),
                    room.getPrice(),
                    room.getStatus(),
                    room.getRoomTypeId(),
                    room.getHotelId());;
        });

    }
    @Override
    public void displayAllRoom() {
        List<Room> rooms = roomRepository.findAll() ;
        System.out.println("\n=== DANH SÁCH ROOM ===");
        System.out.println("ID --------------------- Name ----------------- Price ----------------- Status ----------------- RoomTypeId ----------------- HotelId");
        rooms.forEach(room -> {
            System.out.printf("%s - %s - %s - %s - %s - %s%n",
                    room.getId(),
                    room.getName(),
                    room.getPrice(),
                    room.getStatus(),
                    room.getRoomType().getId(),
                    room.getHotel().getId());
        });

    }


}
