package com.example.nat.clone.service;

import com.example.nat.clone.model.dto.HotelDTO;
import com.example.nat.clone.model.dto.ReservationDTO;
import com.example.nat.clone.model.dto.UserDTO;
import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.model.entity.User;
import com.example.nat.clone.model.entity.Reservation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ReservationService reservationService;
    
    public void exportHotels(String filePath) throws IOException {
        List<Hotel> hotels = hotelService.getAllHotels();
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Hotels");
        
        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên khách sạn");
        headerRow.createCell(2).setCellValue("Địa chỉ");
        headerRow.createCell(3).setCellValue("Tên quản lý");
        headerRow.createCell(4).setCellValue("Số điện thoại");
        headerRow.createCell(5).setCellValue("Số phòng");
        
        // Fill data
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
    }
    
    public void importHotels(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                HotelDTO hotel = HotelDTO.builder()
                                    .name(getCellValueAsString(row.getCell(0)))
                                    .address(getCellValueAsString(row.getCell(1)))
                                    .manager_name(getCellValueAsString(row.getCell(2)))
                                    .manager_phone(getCellValueAsString(row.getCell(3)))
                                    .number_of_room((int) row.getCell(4).getNumericCellValue())
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
    
    public void exportUsers(String filePath) throws IOException {
        List<UserDTO> users = userService.getAllUsers();
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");
        
        // Create header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Số điện thoại");
        headerRow.createCell(4).setCellValue("Địa chỉ");
        
        // Fill data
        int rowNum = 1;
        for (UserDTO user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getPhone());
            row.createCell(4).setCellValue(user.getAddress());
        }
        
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
    
    public void importUsers(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                User user = User.builder()
                    .name(getCellValueAsString(row.getCell(0)))
                    .email(getCellValueAsString(row.getCell(1)))
                    .phone(getCellValueAsString(row.getCell(2)))
                    .address(getCellValueAsString(row.getCell(3)))
                    .dob(getCellValueAsLocalDate(row.getCell(4)))
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .build();
                
                userService.createUser(user);
            }
        }
        
        workbook.close();
        file.close();
        System.out.println("Import khách hàng thành công!");
    }
    
    public void exportReservations(String filePath) throws IOException {
        List<Reservation> reservations = reservationService.getAllReservations();
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservations");
        
        // Create header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên đặt phòng");
        headerRow.createCell(2).setCellValue("Ngày bắt đầu");
        headerRow.createCell(3).setCellValue("Ngày kết thúc");
        headerRow.createCell(4).setCellValue("Ngày đặt");
        headerRow.createCell(5).setCellValue("Ghi chú");
        headerRow.createCell(6).setCellValue("Trạng thái");
        
        // Fill data2
        int rowNum = 1;
        for (Reservation reservation : reservations) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(reservation.getId());
            row.createCell(1).setCellValue(reservation.getName());
            row.createCell(2).setCellValue(reservation.getStartTime().toString());
            row.createCell(3).setCellValue(reservation.getEndTime().toString());
            row.createCell(4).setCellValue(reservation.getReservationTime().toString());
            row.createCell(5).setCellValue(reservation.getNotes());
            row.createCell(6).setCellValue(reservation.getStatus());
        }
        
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
    
    public void importReservations(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                ReservationDTO reservation = ReservationDTO.builder()
                    .name(getCellValueAsString(row.getCell(0)))
                    .StartTime(getCellValueAsLocalDate(row.getCell(1)))
                    .EndTime(getCellValueAsLocalDate(row.getCell(2)))
                    .ReservationTime(getCellValueAsLocalDate(row.getCell(3)))
                    .notes(getCellValueAsString(row.getCell(4)))
                    .status(getCellValueAsString(row.getCell(5)))
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .build();
                
                reservationService.createReservation(reservation);
            }
        }
        
        workbook.close();
        file.close();
        System.out.println("Import đặt phòng thành công!");
    }
    
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
            default: return "";
        }
    }
    
    private LocalDate getCellValueAsLocalDate(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isValidExcelDate(cell.getNumericCellValue())) {
            return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }
}
