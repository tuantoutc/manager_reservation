package com.example.nat.clone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private String id;
    private String name;
    private Double price;
    private String status ; // AVAILABLE, BOOKED, OCCUPIED, OUT_OF_SERVICE
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String roomTypeId; // Foreign key to RoomType
    private String hotelId; // Foreign key to
}
