package com.example.nat.clone.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // This will exclude null fields from the JSON response : cac thanh phan cua json neu null se ko hien thi ra
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

}
