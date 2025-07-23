package com.example.nat.clone.repository;

import com.example.nat.clone.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    // Additional query methods can be defined here if needed
    List<Room> findByStatus(String status);
    List<Room> findByStatusNot(String status);
}
