package com.example.nat.clone.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name = "hotel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String address;
    private String manager_name;
    private String manager_phone;
    private Integer number_of_room;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<RoomType> roomTypes;

    @OneToMany( mappedBy = "hotel",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Room> rooms;


}
