package com.example.nat.clone.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate dob;
}
