package com.example.nat.clone.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate dob;
}
