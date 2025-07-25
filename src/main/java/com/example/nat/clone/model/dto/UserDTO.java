package com.example.nat.clone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO  {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate dob;

}
