package com.example.nat.clone.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    @NotBlank(message = "USER_INVALID")
    @Size(min = 3, max = 50, message = "USER_INVALID")
    private String name;
    @Email(message = "EMAIL_INVALID")
    private String email;
    @NotBlank(message = "PHONE_INVALID")
    private String phone;

    private String address;
    private LocalDate dob;
}
