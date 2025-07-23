package com.example.nat.clone.service;

import com.example.nat.clone.converter.ReservationConverToDTO;
import com.example.nat.clone.exception.AppException;
import com.example.nat.clone.exception.ErrorCode;
import com.example.nat.clone.model.dto.ReservationDTO;
import com.example.nat.clone.model.entity.Hotel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ReservationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;


    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationConverToDTO reservationConverToDTO;

    public List<Reservation>  getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations != null && !reservations.isEmpty()) {
            return reservations;
        } else {
            throw new AppException(ErrorCode.RESERVATION_NOT_FOUND);
        }
    }
    public List<ReservationDTO>  getAllReservationDTO() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO > reservationDTOs = new ArrayList<>();
        if (reservations != null && !reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                 reservationDTOs.add(  reservationConverToDTO.convertToDTO(reservation));
            }
            return reservationDTOs;
        } else {
            throw new AppException(ErrorCode.RESERVATION_NOT_FOUND);
        }

    }

//    public List<Reservation> getAllReservationsByUserId(String userId, String status) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//        if (user != null) {
//            List<Reservation > reservations = reservationRepository.findByNameAndPhoneAndStatus(user.getName(), user.getPhone(), status);
//            if (reservations != null && !reservations.isEmpty()) {
//                return reservations;
//            } else {
//                throw new AppException(ErrorCode.RESERVATION_NOT_FOUND);
//            }
//        }
//        return null;
//    }

    public ReservationDTO getReservationById(String id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            Reservation res = reservation.get();
            return ReservationDTO.builder()
                    .id(res.getId())
                    .name(res.getName())
                    .StartTime(res.getStartTime())
                    .EndTime(res.getEndTime())
                    .notes(res.getNotes())
                    .roomId(res.getRoom().getId())
                    .status(res.getStatus())
                    .ReservationTime(res.getReservationTime())
                    .createdAt(res.getCreatedAt())
                    .updatedAt(res.getUpdatedAt())
                    .build();
        } else {
            throw new AppException(ErrorCode.RESERVATION_NOT_FOUND);
        }
    }




    public Reservation createReservation(ReservationDTO reser) {
        Reservation newReservation = null;
        if(reser.getId() == null || reser.getId().isEmpty()) {
            User user = userRepository.findByNameLikeAndPhone(reser.getUsername(), reser.getPhone());
            if( user == null)  // If user does not exist, create a new user
            {
                User newuser = User.builder()
                        .name(reser.getUsername())
                        .phone(reser.getPhone())
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                        .build();
                user = userRepository.save(newuser);
            }

            Room room =  roomRepository.findById(reser.getRoomId()).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

            newReservation = Reservation.builder()
                    .name(reser.getUsername() + "_" + reser.getPhone() +"_"+LocalDate.now())
                    .StartTime(reser.getStartTime())
                    .EndTime(reser.getEndTime())
                    .notes(reser.getNotes())
                    .user(user)
                    .room(room)
                    .status("Pending") // Default status
                    .ReservationTime(LocalDate.now())
                    .createdAt(reser.getCreatedAt())
                    .updatedAt(reser.getUpdatedAt())
                    .build();
            roomRepository.save(room);
        }
        return reservationRepository.save(newReservation);
    }




    public Reservation updateReservation(ReservationDTO reser) {
        Reservation reser1 =  reservationRepository.findById(reser.getId()).orElseThrow(()-> new AppException(ErrorCode.RESERVATION_NOT_FOUND));

        User user = userRepository.findByNameLikeAndPhone(reser.getUsername(), reser.getPhone());

        if( user == null)  // If user does not exist, create a new user
        {
            User newuser = User.builder()
                    .name(reser.getUsername())
                    .phone(reser.getPhone())
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .build();
            user = userRepository.save(newuser);
        }
        Room room =  roomRepository.findById(reser.getRoomId()).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));
        reser1 = Reservation.builder()
                .id(reser.getId())
                .name(reser.getUsername() + "_" + reser.getPhone() +"_"+LocalDate.now())
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
    public String checkInReservation(String id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESERVATION_NOT_FOUND));
        if (reservation.getStatus().equals("Pending")) {
            reservation.setStatus("Checked_In");
            reservation.setUpdatedAt(LocalDate.now());
            Room room = reservation.getRoom();
            room.setStatus("Unavailable"); // Change room status to unavailable
            roomRepository.save(room);
            return "Checked in successfully";
        } else {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }
    public String checkOutReservation(String id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESERVATION_NOT_FOUND));
        if (reservation.getStatus().equals("Checked_In")) {
            reservation.setStatus("Completed");
            reservation.setUpdatedAt(LocalDate.now());
            Room room = reservation.getRoom();
            room.setStatus("Available"); // Change room status to available
            roomRepository.save(room);
            return "Checked out successfully";
        } else {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }
    public void displayAllReservation() {
        List<Reservation> reservation = getAllReservations();
        System.out.println("\n=== DANH SÁCH ĐẶT PHÒNG ===");
        System.out.println("ID ---------------------------- Name ----------------- Start Time ----------------- End Time ----------------- Status ----------------- Tên người đặt ----------------- Phone----------------- Reservation Time ----------------- tên phòng");
        reservation.forEach(reser -> {
            System.out.printf("%s - %s - %s - %s - %s - %s - %s - %s - %s%n",
                    reser.getId(),
                    reser.getName(),
                    reser.getStartTime(),
                    reser.getEndTime(),
                    reser.getStatus(),
                    reser.getUser().getName(),
                    reser.getUser().getPhone(),
                    reser.getReservationTime(),
                    reser.getRoom().getName());

        });
    }

}
