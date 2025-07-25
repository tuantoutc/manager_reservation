package com.example.nat.clone.service;


import com.example.nat.clone.model.dto.HotelDTO;
import com.example.nat.clone.model.dto.ReservationDTO;
import com.example.nat.clone.model.dto.RoomDTO;
import com.example.nat.clone.model.dto.UserDTO;
import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.model.request.UserCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
public class Excel2Service {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    public void exportHotels(String filePath) throws IOException {
        List<Hotel> hotels = hotelService.getAllHotels();

        if (hotels == null || hotels.isEmpty()) {
            log.info("Hotel list is empty, nothing to export.");
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Hotels");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên khách sạn");
        headerRow.createCell(2).setCellValue("Địa chỉ");
        headerRow.createCell(3).setCellValue("Tên quản lý");
        headerRow.createCell(4).setCellValue("Số điện thoại");
        headerRow.createCell(5).setCellValue("Số phòng");

        // fill data 
        int rowNum = 1;
        for (Hotel hotel : hotels) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(hotel.getId());
            row.createCell(1).setCellValue(hotel.getName());
            row.createCell(2).setCellValue(hotel.getAddress());
            row.createCell(3).setCellValue(hotel.getManager_name());
            row.createCell(4).setCellValue(hotel.getManager_phone());
            row.createCell(5).setCellValue(hotel.getNumber_of_room());
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();


        // Implement the logic to export hotel data to an Excel file
    }


    public void exportUsers(String filePath) throws IOException {
        // Implement the logic to export user data to an Excel file
        List<UserDTO> users = userService.getAllUsers();

        if (users == null || users.isEmpty()) {
            log.info("User list is empty, nothing to export.");
            return; // Exit if there are no users to export
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên khách sạn");
        headerRow.createCell(2).setCellValue("Email khách hàng");
        headerRow.createCell(3).setCellValue("Phone");
        headerRow.createCell(4).setCellValue("Địa chỉ");
        headerRow.createCell(5).setCellValue("Ngày sinh");

        // fill data 
        int rowNum = 1;
        for (UserDTO user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getPhone());
            row.createCell(4).setCellValue(user.getAddress());
            row.createCell(5).setCellValue(user.getDob()); // Handle null birthDate

        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    public void exportReservations(String filePath) throws IOException {
        // Implement the logic to export user data to an Excel file
        List<ReservationDTO> reservations = reservationService.getAllReservationDTO();

        if (reservations == null || reservations.isEmpty()) {
            log.info("Reservation list is empty, nothing to export.");
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservations");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên dặt phòng");
        headerRow.createCell(2).setCellValue("Thời gian bắt đầu");
        headerRow.createCell(3).setCellValue("Thời gian kết thúc");
        headerRow.createCell(4).setCellValue("Thời gian đặt phòng");
        headerRow.createCell(5).setCellValue("Ghi chú");
        headerRow.createCell(6).setCellValue("Trạng thái");
        headerRow.createCell(7).setCellValue("Tên khách hàng");
        headerRow.createCell(8).setCellValue("Số điện thoại");
        headerRow.createCell(9).setCellValue("ID phòng");

        // fill data
        int rowNum = 1;
        for (ReservationDTO reservationDTO : reservations) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reservationDTO.getId());
            row.createCell(1).setCellValue(reservationDTO.getName());
            row.createCell(2).setCellValue(reservationDTO.getStartTime().toString());
            row.createCell(3).setCellValue(reservationDTO.getEndTime().toString());
            row.createCell(4).setCellValue(reservationDTO.getReservationTime().toString());
            row.createCell(5).setCellValue(reservationDTO.getNotes() != null ? reservationDTO.getNotes() : ""); // Handle null notes
            row.createCell(6).setCellValue(reservationDTO.getStatus());
            row.createCell(7).setCellValue(reservationDTO.getUsername());
            row.createCell(8).setCellValue(reservationDTO.getPhone());
            row.createCell(9).setCellValue(reservationDTO.getRoomId() != null ? reservationDTO.getRoomId() : ""); // Handle null roomId


        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    public void exportRoomAvailable(String filePath) throws IOException {
        // Implement the logic to export available room data to an Excel file
        List<RoomDTO> roomDTOS = roomService.getAllRoomsAvailable();

        exportRoomFile(filePath, roomDTOS);

    }

    public void exportRoomUnAvailable(String filePath) throws IOException {
        // Implement the logic to export unavailable room data to an Excel file
        List<RoomDTO> roomDTOS = roomService.getAllRoomsUnAvailable();
        exportRoomFile(filePath, roomDTOS);
    }

    public void exportRoomFile(String filePath, List<RoomDTO> roomDTOS)  throws IOException {

        if (roomDTOS == null || roomDTOS.isEmpty()) {
            log.info("Room list is empty, nothing to export.");
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rooms");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên phòng");
        headerRow.createCell(2).setCellValue("Giá phòng");
        headerRow.createCell(3).setCellValue("Trạng thái");
        headerRow.createCell(4).setCellValue("Loại phòng");
        headerRow.createCell(5).setCellValue("ID khách sạn");

        // fill data

        int rowNum = 1;
        for (RoomDTO roomDTO : roomDTOS) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(roomDTO.getId());
            row.createCell(1).setCellValue(roomDTO.getName());
            row.createCell(2).setCellValue(roomDTO.getPrice());
            row.createCell(3).setCellValue(roomDTO.getStatus());
            String roomType = roomTypeService.getRoomTypeDescriptionById(roomDTO.getRoomTypeId());
            row.createCell(4).setCellValue(roomType); // Handle null roomTypeId
            row.createCell(5).setCellValue(roomDTO.getHotelId() != null ? roomDTO.getHotelId() : ""); // Handle null hotelId
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    public void importHotels(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                HotelDTO hotel = HotelDTO.builder()
                        .name(row.getCell(1).getStringCellValue())
                        .address(row.getCell(2).getStringCellValue())
                        .manager_name(row.getCell(3).getStringCellValue())
                        .manager_phone(row.getCell(4).getStringCellValue())
                        .number_of_room((int) row.getCell(5).getNumericCellValue())
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                        .build();
                hotelService.createHotel(hotel);
            }
        }

        workbook.close();
        file.close();
        System.out.println("Import khách sạn thành công!");

    }

    public void importUsers(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                UserCreateRequest user = UserCreateRequest.builder()
                        .name(row.getCell(1).getStringCellValue())
                        .email(row.getCell(2).getStringCellValue())
                        .phone(row.getCell(3).getStringCellValue())
                        .address(row.getCell(4).getStringCellValue())
                        .dob(row.getCell(5).getLocalDateTimeCellValue().toLocalDate()) // Assuming date is in LocalDate format
                        .build();
                userService.createdUser(user);
            }
        }

        workbook.close();
        file.close();
        System.out.println("Import người dùng thành công!");
    }

    public void importReservations(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                ReservationDTO reservationDTO = ReservationDTO.builder()
                        .name(row.getCell(1).getStringCellValue())
                        .StartTime(row.getCell(2).getLocalDateTimeCellValue().toLocalDate())
                        .EndTime(row.getCell(3).getLocalDateTimeCellValue().toLocalDate())
                        .ReservationTime(row.getCell(4).getLocalDateTimeCellValue().toLocalDate())
                        .notes(row.getCell(5).getStringCellValue())
                        .status(row.getCell(6).getStringCellValue())
                        .username(row.getCell(7).getStringCellValue())
                        .phone(row.getCell(8).getStringCellValue())
                        .roomId(row.getCell(9).getStringCellValue())
                        .build();
                reservationService.createReservation(reservationDTO);
            }
        }

        workbook.close();
        file.close();
        System.out.println("Import đặt phòng thành công!");
    }

    public void importRooms(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                RoomDTO roomDTO = RoomDTO.builder()
                        .name(row.getCell(1).getStringCellValue())
                        .price(row.getCell(2).getNumericCellValue())
                        .status(row.getCell(3).getStringCellValue())
                        .roomTypeId(row.getCell(4).getStringCellValue())
                        .hotelId(row.getCell(5).getStringCellValue())
                        .build();
                roomService.createRoom(roomDTO);
            }
        }

        workbook.close();
        file.close();
        System.out.println("Import phòng thành công!");
    }

}
