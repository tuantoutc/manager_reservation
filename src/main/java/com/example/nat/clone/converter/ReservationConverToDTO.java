package com.example.nat.clone.converter;

import com.example.nat.clone.model.dto.ReservationDTO;
import com.example.nat.clone.model.entity.Reservation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  ReservationConverToDTO  {

    @Autowired
    ModelMapper modelMapper;
    public ReservationDTO convertToDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDTO dto = modelMapper.map(reservation, ReservationDTO.class);

        if (reservation.getUser() != null) {
            dto.setUsername(reservation.getUser().getName());
            dto.setPhone(reservation.getUser().getPhone());
        }
        if (reservation.getRoom() != null) {
            dto.setRoomId(reservation.getRoom().getId());
        }

        return dto;
    }
}
