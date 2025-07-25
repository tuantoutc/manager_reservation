package com.example.nat.clone.service;

import com.example.nat.clone.TableFormatter;
import com.example.nat.clone.exception.AppException;
import com.example.nat.clone.exception.ErrorCode;
import com.example.nat.clone.model.dto.HotelDTO;
import com.example.nat.clone.model.dto.RoomTypeDTO;
import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.model.entity.RoomType;
import com.example.nat.clone.repository.HotelRepository;
import com.example.nat.clone.repository.RoomTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    
    public Optional<Hotel> getHotelById(String id) {
        return hotelRepository.findById(id);
    }

    public Hotel createHotel(HotelDTO hotelDTO) {

        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotel.setCreatedAt(LocalDate.now());
        hotel.setUpdatedAt(LocalDate.now());

        insertRoomTypes(hotelDTO, hotel);

        return  hotelRepository.save(hotel); // Cascade sẽ tự động save RoomType

    }
    
    public Hotel updateHotel( HotelDTO hotelDTO) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelDTO.getId());
        if (hotel.isPresent()) {
            Hotel hotel1 = hotel.get();
            hotel1 = modelMapper.map(hotelDTO, Hotel.class);
            hotel1.setUpdatedAt(LocalDate.now());
            hotel1.setRoomTypes(null);

            insertRoomTypes(hotelDTO, hotel1);

            return hotelRepository.save(hotel1);
        }
        return null;
    }
    
    public void deleteHotel(String id) {
        if(id == null || id.isEmpty()) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));
        hotelRepository.deleteById(id);
    }
    
//    public void displayAllHotels() {
//        List<Hotel> hotels = getAllHotels();
//        System.out.println("\n=== DANH SÁCH KHÁCH SẠN ===");
//        System.out.println("ID ---------------------------- Name ----------------- Address ----------------- Number Of Room ----------------- Manager Name ----------------- Manager Phone");
//        hotels.forEach(hotel -> {
//            System.out.printf("%s - %s - %s - %s - %s - %s%n",
//                    hotel.getId(),
//                    hotel.getName(),
//                    hotel.getAddress(),
//                    hotel.getNumber_of_room(),
//                    hotel.getManager_name(),
//                    hotel.getManager_phone());
//        });
//    }
// Trong HotelService
        public void displayAllHotels() {
            List<Hotel> hotels = hotelRepository.findAll();

            String[] headers = {"ID", "Tên khách sạn", "Địa chỉ", "Quản lý", "SĐT quản lý", "Số phòng"};
            String[][] data = new String[hotels.size()][6];

            for (int i = 0; i < hotels.size(); i++) {
                Hotel hotel = hotels.get(i);
                data[i][0] = hotel.getId();
                data[i][1] = hotel.getName();
                data[i][2] = hotel.getAddress();
                data[i][3] = hotel.getManager_name();
                data[i][4] = hotel.getManager_phone();
                data[i][5] = String.valueOf(hotel.getNumber_of_room());
            }

            TableFormatter.printTable(headers, data);
        }


    private void insertRoomTypes(HotelDTO hotelDTO, Hotel hotel) {
        if(hotelDTO.getRoomTypes() != null && !hotelDTO.getRoomTypes().isEmpty()) {
            List<RoomType> roomTypes = hotelDTO.getRoomTypes().stream()
                    .map(dto -> RoomType.builder()
                            .name(dto.getName())
                            .description(dto.getDescription())
                            .hotel(hotel)
                            .createdAt(LocalDate.now())
                            .updatedAt(LocalDate.now())
                            .build())
                    .toList();
            hotel.setRoomTypes(roomTypes);
        }
    }
}
