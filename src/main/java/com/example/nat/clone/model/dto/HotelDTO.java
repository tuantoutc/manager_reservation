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
public class HotelDTO {
    private String id;
    private String name;
    private String address;
    private String manager_name;
    private String manager_phone;
    private Integer number_of_room;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
