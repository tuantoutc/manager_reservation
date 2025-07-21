package com.example.nat.clone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReservationDTO {
    private String id;
    private String name;
    private LocalDate StartTime;
    private LocalDate EndTime;
    private LocalDate ReservationTime;
    private String notes;
    private String status ; // PENDING, CONFIRMED, CHECKED_IN, COMPLETED
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String userId;
    private String roomId;
}
