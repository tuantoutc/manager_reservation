package com.example.nat.clone.service;

import com.example.nat.clone.model.entity.RoomType;
import com.example.nat.clone.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public String getRoomTypeDescriptionById(String id) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room type not found"));
        return roomType.getDescription();
    }
}
