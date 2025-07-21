package com.example.nat.clone;

import com.example.nat.clone.service.HotelService;
import com.example.nat.clone.service.ReservationService;
import com.example.nat.clone.service.UserService;
import com.example.nat.clone.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class HotelReservationApplication implements CommandLineRunner {

    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private ExcelService excelService;

    private Scanner scanner = new Scanner(System.in);
    private boolean useDatabase = true;

    public static void main(String[] args) {
        SpringApplication.run(HotelReservationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== HỆ THỐNG ĐẶT PHÒNG KHÁCH SẠN ===");
        chooseDataSource();
        showMainMenu();
    }

    private void chooseDataSource() {
        System.out.println("\nChọn nguồn dữ liệu:");
        System.out.println("1. Database");
        System.out.println("2. Excel File");
        System.out.print("Lựa chọn của bạn: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        useDatabase = (choice == 1);
        System.out.println(useDatabase ? "Đã chọn Database" : "Đã chọn Excel File");
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\n=== MENU CHÍNH ===");
            System.out.println("1. Quản lý khách sạn");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý đặt phòng");
            System.out.println("4. Import dữ liệu từ Excel");
            System.out.println("5. Export dữ liệu ra Excel");
            System.out.println("6. Chuyển đổi nguồn dữ liệu");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: hotelManagementMenu(); break;
                case 2: userManagementMenu(); break;
                case 3: reservationManagementMenu(); break;
                case 4: importFromExcel(); break;
                case 5: exportToExcel(); break;
                case 6: chooseDataSource(); break;
                case 0: 
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    return;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void hotelManagementMenu() {
        System.out.println("\n=== QUẢN LÝ KHÁCH SẠN ===");
        System.out.println("1. Xem danh sách khách sạn");
        System.out.println("2. Thêm khách sạn mới");
        System.out.println("3. Cập nhật thông tin khách sạn");
        System.out.println("4. Xóa khách sạn");
        System.out.println("0. Quay lại");
        // Implementation will be handled by service layer
        System.out.print("Lựa chọn: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: hotelService.displayAllHotels(); break;
            case 2: hotelService.getAllHotels(); break;
            case 3: hotelService.getAllHotels(); break;
            case 4: hotelService.getAllHotels(); break;
            case 0:
                System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                return;
            default: System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void userManagementMenu() {
        System.out.println("\n=== QUẢN LÝ KHÁCH HÀNG ===");
        System.out.println("1. Xem danh sách khách hàng");
        System.out.println("2. Thêm khách hàng mới");
        System.out.println("3. Cập nhật thông tin khách hàng");
        System.out.println("4. Xóa khách hàng");
        System.out.println("0. Quay lại");
        System.out.print("Lựa chọn: ");
        // Implementation will be handled by service layer
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: userService.displayAllUser(); break;
            case 2: System.out.println("Chức năng thêm người dùng mới!"); break;
            case 3: System.out.println("Chức năng cập nhật thông tin khách hàng!"); break;
            case 4: System.out.println("Chức năng xóa khách hàng !"); break;

            case 0:
                System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                return;
            default: System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void reservationManagementMenu() {
        System.out.println("\n=== QUẢN LÝ ĐẶT PHÒNG ===");
        System.out.println("1. Xem danh sách đặt phòng");
        System.out.println("2. Tạo đặt phòng mới");
        System.out.println("3. Cập nhật trạng thái đặt phòng");
        System.out.println("4. Hủy đặt phòng");
        System.out.println("5. Check-in");
        System.out.println("6. Check-out");
        System.out.println("0. Quay lại");
        // Implementation will be handled by service layer

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: reservationService.getAllReservations(); break;
            case 2: System.out.println("Chức năng thêm dat phong  mới!"); break;
            case 3: System.out.println("Chức năng cập nhật thông tin dat phong!"); break;
            case 4: System.out.println("Chức năng xóa dat phong !"); break;

            case 0:
                System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                return;
            default: System.out.println("Lựa chọn không hợp lệ!");
        }

    }

    private void importFromExcel() {
        System.out.println("\n=== IMPORT DỮ LIỆU TỪ EXCEL ===");
        System.out.println("1. Import khách sạn");
        System.out.println("2. Import khách hàng");
        System.out.println("3. Import đặt phòng");
        System.out.print("Lựa chọn: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nhập đường dẫn file Excel: ");
        String filePath = scanner.nextLine();
        
        try {
            switch (choice) {
                case 1: excelService.importHotels(filePath); break;
                case 2: excelService.importUsers(filePath); break;
                case 3: excelService.importReservations(filePath); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi import: " + e.getMessage());
        }
    }

    private void exportToExcel() {
        System.out.println("\n=== EXPORT DỮ LIỆU RA EXCEL ===");
        System.out.println("1. Export khách sạn");
        System.out.println("2. Export khách hàng");
        System.out.println("3. Export đặt phòng");
        System.out.print("Lựa chọn: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nhập đường dẫn file xuất: ");
        String filePath = scanner.nextLine();
        
        try {
            switch (choice) {
                case 1: excelService.exportHotels(filePath); break;
                case 2: excelService.exportUsers(filePath); break;
                case 3: excelService.exportReservations(filePath); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
            System.out.println("Export thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi export: " + e.getMessage());
        }
    }


    private void GetOutCosole() {
        System.out.println("----Danh sách khách hàng----");
        System.out.println("ID --------------------- Name ----------------- Email ----------------- Phone ----------------- Address ----------------- DOB");
        userService.getAllUsers().forEach(user -> {
            System.out.printf("%s - %s - %s - %s - %s - %s%n",
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getAddress(),
                    user.getDob());
        });
    }
}
