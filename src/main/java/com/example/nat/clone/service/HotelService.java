package com.example.nat.clone.service;

import com.example.nat.clone.exception.AppException;
import com.example.nat.clone.exception.ErrorCode;
import com.example.nat.clone.model.dto.HotelDTO;
import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private HotelRepository hotelRepository;
    
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    
    public Optional<Hotel> getHotelById(String id) {
        return hotelRepository.findById(id);
    }
    
    public Hotel createHotel(HotelDTO hotel) {
        Hotel hotel1 = null;
        if (hotel.getId() == null || hotel.getId().isEmpty()) {
            hotel1 = modelMapper.map(hotel, Hotel.class);
        }
        return hotelRepository.save(hotel1);
    }
    
    public Hotel updateHotel( HotelDTO hotel) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotel.getId());
        if (hotelOpt.isPresent()) {
            Hotel hotel1 = hotelOpt.get();
            hotel1 = modelMapper.map(hotel, Hotel.class);
            hotel1.setUpdatedAt(LocalDate.now());
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
    
    public void displayAllHotels() {
        List<Hotel> hotels = getAllHotels();
        System.out.println("\n=== DANH SÁCH KHÁCH SẠN ===");
        System.out.println("ID ---------------------------- Name ----------------- Address ----------------- Number Of Room ----------------- Manager Name ----------------- Manager Phone");
        hotels.forEach(hotel -> {
            System.out.printf("%s - %s - %s - %s - %s - %s%n",
                    hotel.getId(),
                    hotel.getName(),
                    hotel.getAddress(),
                    hotel.getNumber_of_room(),
                    hotel.getManager_name(),
                    hotel.getManager_phone());
        });
    }
}
