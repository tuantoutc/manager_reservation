package com.example.nat.clone.service;

import com.example.nat.clone.exception.AppException;
import com.example.nat.clone.exception.ErrorCode;
import com.example.nat.clone.model.dto.RoomDTO;
import com.example.nat.clone.model.dto.RoomTypeDTO;
import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.model.entity.RoomType;
import com.example.nat.clone.repository.HotelRepository;
import com.example.nat.clone.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public String getRoomTypeDescriptionById(String id) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room type not found"));
        return roomType.getDescription();
    }
    public List<RoomTypeDTO> getAllRoomTypes( String hotelId)
    {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));

        List<RoomType> roomTypes = roomTypeRepository.findByHotel(hotel);
        if (roomTypes.isEmpty()) {
            throw new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND);
        }
        return roomTypes.stream().map(
                                roomType -> RoomTypeDTO.builder()
                                        .id(roomType.getId())
                                        .name(roomType.getName())
                                        .description(roomType.getDescription())
                                        .build()
                                    ).toList();
    }

    public void getRoomTypeByHotelId(String id) {
        List<RoomTypeDTO> roomTypes = getAllRoomTypes(id);
        if (roomTypes.isEmpty()) {
            throw new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND);
        }
        else {
            System.out.println("Các loại phòng của khách sạn là:");
            System.out.println("ID ---------------------------- Name ----------------- Description");
            roomTypes.forEach(roomTypeDTO -> {
                System.out.printf("%s - %s - %s%n",
                        roomTypeDTO.getId(),
                        roomTypeDTO.getName(),
                        roomTypeDTO.getDescription()
                );

            });
        }
    }

    public void createRoomType(RoomTypeDTO roomTypeDTO, String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));
        if(roomTypeRepository.existsByNameIgnoreCaseAndHotel(roomTypeDTO.getName(), hotel)) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        RoomType roomType = new RoomType();
        roomType.setName(roomTypeDTO.getName());
        roomType.setDescription(roomTypeDTO.getDescription());
        roomType.setHotel(hotel);

        roomTypeRepository.save(roomType);
    }
    public void updateRoomType(RoomTypeDTO roomTypeDTO) {
        RoomType roomType = roomTypeRepository.findById(roomTypeDTO.getId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND));
        roomType.setName(roomTypeDTO.getName());
        roomType.setDescription(roomTypeDTO.getDescription());

        roomTypeRepository.save(roomType);
    }
    public void deleteRoomType(String id) {
        if (id == null || id.isEmpty()) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_TYPE_NOT_FOUND));
        roomTypeRepository.deleteById(id);
    }




}
