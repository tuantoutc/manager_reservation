package com.example.nat.clone.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "reservation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private LocalDate StartTime;
    private LocalDate EndTime;
    private LocalDate ReservationTime;
    private String notes;
    private String status = "PENDING"; // PENDING, CONFIRMED, CHECKED_IN, COMPLETED
    private LocalDate createdAt;
    private LocalDate updatedAt;


   @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", userId='" + (user != null ? user.getId() : null) + '\'' +
                ", roomId='" + (room != null ? room.getId() : null) + '\'' +
                ", startTime=" + StartTime +
                ", endTime=" + EndTime +
                ", reservationTime=" + ReservationTime +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
