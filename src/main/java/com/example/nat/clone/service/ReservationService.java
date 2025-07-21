package com.example.nat.clone.service;

import com.example.nat.clone.exception.AppException;
import com.example.nat.clone.exception.ErrorCode;
import com.example.nat.clone.model.dto.ReservationDTO;
import com.example.nat.clone.model.entity.Reservation;
import com.example.nat.clone.model.entity.Room;
import com.example.nat.clone.model.entity.User;
import com.example.nat.clone.repository.ReservationRepository;
import com.example.nat.clone.repository.RoomRepository;
import com.example.nat.clone.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class ReservationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;


    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation>  getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(ReservationDTO reser) {
        Reservation newReservation = null;
        if(reser.getId() == null || reser.getId().isEmpty()) {
            User user =  userRepository.findById(reser.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            Room room =  roomRepository.findById(reser.getRoomId()).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
            newReservation = Reservation.builder()
                    .name(reser.getName())
                    .StartTime(reser.getStartTime())
                    .EndTime(reser.getEndTime())
                    .notes(reser.getNotes())
                    .user(user)
                    .room(room)
                    .ReservationTime(reser.getReservationTime())
                    .createdAt(reser.getCreatedAt())
                    .updatedAt(reser.getUpdatedAt())
                    .build();
        }
        return reservationRepository.save(newReservation);
    }


    public Reservation getReservationById(String id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }

    public Reservation updateReservation(ReservationDTO reser) {
        Reservation reser1 =  reservationRepository.findById(reser.getId()).orElseThrow(()-> new AppException(ErrorCode.RESERVATION_NOT_FOUND));

        User user =  userRepository.findById(reser.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Room room =  roomRepository.findById(reser.getRoomId()).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        reser1 = Reservation.builder()
                .id(reser.getId())
                .name(reser.getName())
                .StartTime(reser.getStartTime())
                .EndTime(reser.getEndTime())
                .notes(reser.getNotes())
                .user(user)
                .room(room)
                .ReservationTime(reser.getReservationTime())
                .createdAt(LocalDate.now()) // Assuming you want to update createdAt to now
                .updatedAt(LocalDate.now()) // Assuming you want to update updatedAt to now
                .status(reser.getStatus()) // Assuming status is part of the DTO
                .build();

        return reservationRepository.save(reser1);
    }

    public String deleteReservation(String id) {
        if(reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return "Deleted successfully";
        } else {
            throw new AppException(ErrorCode.RESERVATION_NOT_FOUND);
        }
    }

}
