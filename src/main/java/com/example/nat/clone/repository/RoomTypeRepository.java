package com.example.nat.clone.repository;

import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.model.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {


    List<RoomType> findByHotel(Hotel hotel);

    boolean existsByNameIgnoreCaseAndHotel(String name, Hotel hotel); // Kiểm tra xem RoomType đã tồn tại trong Hotel hay chưa

}

