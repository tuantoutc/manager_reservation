package com.example.nat.clone.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetDTO {
    private String id;
    private String name;
    private String Description;
    private String roomTypeId; // Foreign key to RoomType

}
