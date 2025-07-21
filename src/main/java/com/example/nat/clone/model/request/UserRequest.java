package com.example.nat.clone.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    @NotBlank(message = "name cannot be blank")
    @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters")
    private String name;
    @Email(message = "email should be valid")
    private String email;
    private String phone;
    private String address;
    private LocalDate dob;

}
