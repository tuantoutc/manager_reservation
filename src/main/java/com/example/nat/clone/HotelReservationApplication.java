package com.example.nat.clone;

import com.example.nat.clone.model.dto.*;
import com.example.nat.clone.model.entity.User;
import com.example.nat.clone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class HotelReservationApplication implements CommandLineRunner {

    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private Excel2Service excel2Service;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private AssetService assetService;

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
            System.out.println("4. Quản lý phòng");
            System.out.println("5. Import dữ liệu từ Excel");
            System.out.println("6. Export dữ liệu ra Excel");
            System.out.println("7. Chuyển đổi nguồn dữ liệu");
            System.out.println("8. Quản lý loại phòng và tài sản");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: hotelManagementMenu(); break;
                case 2: userManagementMenu(); break;
                case 3: reservationManagementMenu(); break;
                case 4: roomManagementMenu(); break;

                case 5: importFromExcel(); break;
                case 6: exportToExcel(); break;
                case 7: chooseDataSource(); break;
                case 8: roomTypeAndAssetManagementMenu(); break;

                case 0: 
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                    return;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void roomManagementMenu() {
        System.out.println("\n=== QUẢN LÝ PHÒNG ===");
        System.out.println("1. Xem danh sách phòng");
        System.out.println("2. Xem danh sách phòng trống");
        System.out.println("3. Xem danh sách phòng đã thuê");
        System.out.println("4. Thêm phòng");
        System.out.println("5. Cập nhật thông tin phòng");
        System.out.println("6. Xóa phòng");
        System.out.println("0. Quay lại");
        // Implementation will be handled by service layer
        System.out.print("Lựa chọn: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: roomService.displayAllRoom(); break;
            case 2: roomService.displayAllRoomAvailable(); break;
            case 3: roomService.displayAllRoomUnAvailable(); break;
            case 4: createRoom(); break;
            case 5: updateRoom(); break;
            case 6: deleteRoom(); break;
            case 0:
                System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                return;
            default: System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void hotelManagementMenu() {
        System.out.println("\n=== QUẢN LÝ KHÁCH SẠN ===");
        System.out.println("1. Xem danh sách khách sạn");
        System.out.println("2. Thêm khách sạn mới");
        System.out.println("3. Cập nhật thông tin khách sạn");
        System.out.println("4. Xóa khách sạn");
        System.out.println("5. Cập nhật thông tin cho loại phòng của khách sạn");
        System.out.println("0. Quay lại");
        // Implementation will be handled by service layer
        System.out.print("Lựa chọn: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: hotelService.displayAllHotels(); break;
            case 2: createHotel(); break;
            case 3: updateHotel(); break;
            case 4: deleteHotel(); break;
            case 5: deleteHotel(); break;
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
            case 2: createUser(); break;
            case 3: updateUser(); break;
            case 4: deleteUser(); break;
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
        System.out.print("Lựa chọn: ");


        // Implementation will be handled by service layer

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: reservationService.displayAllReservation(); break;
            case 2: createReservation(); break;
            case 3: updateReservation(); break;
            case 4: deleteReservation(); break;
            case 5: checkInReservation(); break;
            case 6: checkOutReservation(); break;

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
                case 1: excel2Service.importHotels(filePath); break;
                case 2: excel2Service.importUsers(filePath); break;
                case 3: excel2Service.importReservations(filePath); break;
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
        System.out.println("4. Export phòng trống");
        System.out.println("5. Export phòng đã thuê");


        System.out.print("Lựa chọn: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nhập đường dẫn file xuất: ");
        String filePath = scanner.nextLine();
        
        try {
            switch (choice) {
                case 1: excel2Service.exportHotels(filePath); break;
                case 2: excel2Service.exportUsers(filePath); break;
                case 3: excel2Service.exportReservations(filePath); break;
                case 4: excel2Service.exportRoomAvailable(filePath); break;
                case 5: excel2Service.exportRoomUnAvailable(filePath); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
            System.out.println("Export thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi export: " + e.getMessage());
        }
    }

    private void roomTypeAndAssetManagementMenu() {
        System.out.println("\n=== QUẢN LÝ LOẠI PHÒNG VÀ TÀI SẢN ===");
        System.out.println("1. Xem các loại phòng của khách sạn");
        System.out.println("2. Thêm loại phòng mới cho khách sạn");
        System.out.println("3. Cập nhật thông tin loại phòng của khách sạn");
        System.out.println("4. Xóa loại phòng của khách sạn");
        System.out.println("5. Thêm tài sản cho loại phòng của khách sạn");
        System.out.println("6. Cập nhật thông tin tài sản của loại phòng");
        System.out.println("7. Xóa tài sản của loại phòng");
        System.out.println("0. Quay lại");
        // Implementation will be handled by service layer
        System.out.print("Lựa chọn: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: getRoomTypeByHotelId(); break;
            case 2: createRoomType(); break;
            case 3: updateRoomType(); break;
            case 4: deleteRoomType(); break;
            case 5: createAsset(); break;
            case 6: updateAsset(); break;
            case 7: deleteAsset(); break;

            case 0:
                System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                return;
            default: System.out.println("Lựa chọn không hợp lệ!");
        }
    }


    private void createHotel() {
        System.out.println("Chức năng thêm khách sạn mới!");
        HotelDTO hotel = new HotelDTO();
        System.out.print("Nhập tên khách sạn: ");
        hotel.setName(scanner.nextLine());
        System.out.print("Nhập địa chỉ khách sạn: ");
        hotel.setAddress(scanner.nextLine());
        System.out.print("Nhập tên quản lý khách sạn: ");
        hotel.setManager_name(scanner.nextLine());
        System.out.print("Nhập số điện thoại quản lý khách sạn: ");
        hotel.setManager_phone(scanner.nextLine());
        System.out.print("Nhập số lượng phòng: ");
        hotel.setNumber_of_room(scanner.nextInt());

        scanner.nextLine();// Thêm dòng này để consume ký tự xuống dòng

        List<RoomTypeDTO> roomTypeDTOS = new ArrayList<>();
        System.out.println("Nhập thông tin loại phòng mà khách sạn có (nhập 'done' để kết thúc):");
        while (true) {
            RoomTypeDTO  roomType = new RoomTypeDTO();
            System.out.print("Tên loại phòng: ");
            String roomTypeName = scanner.nextLine();
            if (roomTypeName.equalsIgnoreCase("done")) {
                break;
            }
            roomType.setName(roomTypeName);
            System.out.print("Nhập mô tả loại phòng: ");
            roomType.setDescription(scanner.nextLine());

            roomTypeDTOS.add(roomType);
        }
        hotel.setRoomTypes(roomTypeDTOS);

        hotelService.createHotel(hotel);
    }
    private void updateHotel() {
        System.out.println("Chức năng cập nhật khách sạn!");
        HotelDTO hotel = new HotelDTO();
        System.out.print("Nhập ID khách sạn cần cập nhật: ");
        hotel.setId(scanner.nextLine());
        System.out.print("Nhập tên khách sạn: ");
        hotel.setName(scanner.nextLine());
        System.out.print("Nhập địa chỉ khách sạn: ");
        hotel.setAddress(scanner.nextLine());
        System.out.print("Nhập tên quản lý khách sạn: ");
        hotel.setManager_name(scanner.nextLine());
        System.out.print("Nhập số điện thoại quản lý khách sạn: ");
        hotel.setManager_phone(scanner.nextLine());
        System.out.print("Nhập số lượng phòng: ");
        hotel.setNumber_of_room(scanner.nextInt());

        scanner.nextLine();// Thêm dòng này để consume ký tự xuống dòng

        List<RoomTypeDTO> roomTypeDTOS = new ArrayList<>();
        System.out.println("Nhập thông tin loại phòng mà khách sạn có (nhập 'done' để kết thúc):");
        while (true) {
            RoomTypeDTO  roomType = new RoomTypeDTO();
            System.out.print("Tên loại phòng: ");
            String roomTypeName = scanner.nextLine();
            if (roomTypeName.equalsIgnoreCase("done")) {
                break;
            }
            roomType.setName(roomTypeName);
            System.out.print("Nhập mô tả loại phòng: ");
            roomType.setDescription(scanner.nextLine());

            roomTypeDTOS.add(roomType);
        }
        hotel.setRoomTypes(roomTypeDTOS);

        hotelService.updateHotel(hotel);
    }
    private void deleteHotel() {
        System.out.println("Chức năng xóa khách sạn!");
        System.out.print("Nhập ID khách sạn cần xóa: ");
        hotelService.deleteHotel(scanner.nextLine());
    }
    private void createUser()
    {
        User user = new User();

        System.out.println("Chức năng thêm người dùng mới!");
        System.out.print("Nhập tên khách hàng: ");
        user.setName(scanner.nextLine());
        System.out.print("Nhập email khách hàng: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Nhập số điện thoại khách hàng: ");
        user.setPhone( scanner.nextLine());
        System.out.print("Nhập địa chỉ khách hàng: ");
        user.setAddress( scanner.nextLine());
        System.out.print("Nhập ngày sinh khách hàng (yyyy-MM-dd): ");
        user.setDob(LocalDate.parse( scanner.nextLine()));
        userService.createUser(user);
    }

    private void updateUser() {
        UserDTO user = new UserDTO();
        System.out.println("Chức năng cập nhật thông tin khách hàng!");
        System.out.print("Nhập ID khách hàng cần cập nhật: ");
        user.setId(scanner.nextLine());
        System.out.print("Nhập tên khách hàng: ");
        user.setName(scanner.nextLine());
        System.out.print("Nhập email khách hàng: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Nhập số điện thoại khách hàng: ");
        user.setPhone( scanner.nextLine());
        System.out.print("Nhập địa chỉ khách hàng: ");
        user.setAddress( scanner.nextLine());
        System.out.print("Nhập ngày sinh khách hàng (yyyy-MM-dd): ");
        user.setDob(LocalDate.parse( scanner.nextLine()));

        userService.updatedUser(user);
    }
    private void deleteUser() {
        System.out.println("Chức năng xóa khách hàng!");
        System.out.print("Nhập ID khách hàng cần xóa: ");
        String deleteUserId = scanner.nextLine();
        userService.deleteUser(deleteUserId);
    }
    private void deleteRoom() {
        System.out.println("Chức năng xóa phòng!");
        System.out.print("Nhập ID phòng cần xóa: ");
        String deleteUserId = scanner.nextLine();
        roomService.deleteRoom(deleteUserId);
    }
    private void updateRoom() {

        System.out.println("Chức năng cập nhật thông tin phòng!");
        RoomDTO room = new RoomDTO();
        System.out.print("Nhập ID phòng cần cập nhật: ");
        room.setId(scanner.nextLine());
        System.out.print("Nhập tên phòng: ");
        room.setName(scanner.nextLine());
        System.out.print("Nhập giá phòng: ");
        room.setPrice(Double.parseDouble(scanner.nextLine()));
        System.out.print("Nhập trạng thái phòng (available/unavailable): ");
        room.setStatus(scanner.nextLine());


        System.out.print("Nhập ID khách sạn: ");
        room.setHotelId(scanner.nextLine());

        List<RoomTypeDTO > roomTypes = roomTypeService.getAllRoomTypes(room.getHotelId());
        if(roomTypes.isEmpty()) {
            System.out.println("Không có loại phòng nào được định nghĩa cho khách sạn này.");
            return;
        }
        else{
            System.out.println("Các loại phòng có sẵn: ");
            for (int i = 0; i < roomTypes.size(); i++) {
                RoomTypeDTO roomType = roomTypes.get(i);
                System.out.printf("%s. %s - %s%n", roomType.getId() , roomType.getName(), roomType.getDescription());
            }
            System.out.print("Nhập ID loại phòng: ");
            room.setRoomTypeId(scanner.nextLine());
        }

        roomService.updateRoom(room);
    }

    private void createRoom() {
        System.out.println("Chức năng thêm phòng mới!");
        RoomDTO room = new RoomDTO();
        System.out.print("Nhập tên phòng: ");
        room.setName(scanner.nextLine());
        System.out.print("Nhập giá phòng: ");
        room.setPrice(Double.parseDouble(scanner.nextLine()));
        System.out.print("Nhập trạng thái phòng (available/unavailable): ");
        room.setStatus(scanner.nextLine());
        System.out.print("Nhập ID khách sạn: ");
        room.setHotelId(scanner.nextLine());

        // Assuming roomTypeId and hotelId are required for creating
        List<RoomTypeDTO > roomTypes = roomTypeService.getAllRoomTypes(room.getHotelId());
        if(roomTypes.isEmpty()) {
            System.out.println("Không có loại phòng nào được định nghĩa cho khách sạn này.");
            return;
        }
        else{
            System.out.println("Các loại phòng có sẵn: ");
            for (int i = 0; i < roomTypes.size(); i++) {
                RoomTypeDTO roomType = roomTypes.get(i);
                System.out.printf("%s - %s - %s%n", roomType.getId() , roomType.getName(), roomType.getDescription());
            }
            System.out.print("Nhập ID loại phòng: ");
            room.setRoomTypeId(scanner.nextLine());
        }
        roomService.createRoom(room);
    }

    private void createReservation() {
        System.out.println("Chức năng tạo đặt phòng mới!");

        System.out.println(" Các phòng có sẵn: ");
        roomService.displayAllRoomAvailable();

        ReservationDTO reservation = new ReservationDTO();
        System.out.print("Nhập ID phòng cần đặt: ");
        reservation.setRoomId(scanner.nextLine());
        System.out.print("Nhập tên của bạn: ");
        reservation.setUsername(scanner.nextLine());
        System.out.print("Nhập số điện thoại của bạn: ");
        reservation.setPhone(scanner.nextLine());

        System.out.print("Nhập ngày bắt đầu đặt phòng (yyyy-MM-dd): ");
        reservation.setStartTime(LocalDate.parse(scanner.nextLine()));

        System.out.print("Nhập ngày kết thúc đặt phòng (yyyy-MM-dd): ");
        reservation.setEndTime(LocalDate.parse(scanner.nextLine()));

        System.out.print("Nhập ghi chú (nếu có): ");
        reservation.setNotes(scanner.nextLine());
        reservation.setCreatedAt(LocalDate.now());
        reservation.setUpdatedAt(LocalDate.now());
        reservation.setStatus("Pending");
        reservationService.createReservation(reservation);

    }

    private void updateReservation() {
        System.out.println("Chức năng cập nhật thông tin đặt phòng!");

        ReservationDTO reservation = new ReservationDTO();

        System.out.print("Nhập mã đặt phòng cần cập nhật: ");
        reservation.setId(scanner.nextLine());

        System.out.println(" Các phòng có sẵn: ");
        roomService.displayAllRoomAvailable();

        System.out.print("Nhập ID phòng cần đặt: ");
        String roomId = scanner.nextLine();
        reservation.setRoomId(roomId);
        System.out.print("Nhập tên của bạn: ");
        reservation.setUsername(scanner.nextLine());
        System.out.print("Nhập số điện thoại của bạn: ");
        reservation.setPhone(scanner.nextLine());
        System.out.print("Nhập ngày bắt đầu đặt phòng (yyyy-MM-dd): ");
        reservation.setStartTime(LocalDate.parse(scanner.nextLine()));
        System.out.print("Nhập ngày kết thúc đặt phòng (yyyy-MM-dd): ");
        reservation.setEndTime(LocalDate.parse(scanner.nextLine()));
        reservation.setReservationTime(LocalDate.now());
        System.out.print("Nhập ghi chú (nếu có): ");
        reservation.setNotes(scanner.nextLine());
        System.out.print("Nhập trạng thái đặt phòng (Pending/Confirmed/Canceled): ");
        reservation.setStatus(scanner.nextLine());
        reservation.setUpdatedAt(LocalDate.now());
        reservationService.updateReservation(reservation);

    }
    private void deleteReservation() {
        System.out.println("Chức năng xóa đặt phòng!");
        System.out.print("Nhập ID đặt phòng cần xóa: ");
        String reservationId = scanner.nextLine();
        reservationService.deleteReservation(reservationId);
    }
    private void checkInReservation() {
        System.out.println("Chức năng check-in đặt phòng!");
        System.out.print("Nhập ma đặt phòng cần check-in: ");
        String reservationId = scanner.nextLine();
        reservationService.checkInReservation(reservationId);
    }
    private void checkOutReservation() {
        System.out.println("Chức năng check-out đặt phòng!");
        System.out.print("Nhập ID đặt phòng cần check-out: ");
        String reservationId = scanner.nextLine();
        reservationService.checkOutReservation(reservationId);
    }
    private void getRoomTypeByHotelId() {
        System.out.println("Xem các loại phòng của khách sạn");
        System.out.print("Nhập ID của khạch sạn: ");
        String hotelId = scanner.nextLine();
        roomTypeService.getRoomTypeByHotelId(hotelId);
    }

    private void createRoomType() {
        System.out.println("Chức năng thêm loại phòng mới cho khách sạn!");
        RoomTypeDTO roomType = new RoomTypeDTO();
        System.out.print("Nhập tên loại phòng: ");
        roomType.setName(scanner.nextLine());
        System.out.print("Nhập mô tả loại phòng: ");
        roomType.setDescription(scanner.nextLine());
        System.out.print("Nhập ID khách sạn: ");
        String hotelId = scanner.nextLine();

        roomTypeService.createRoomType(roomType, hotelId);
    }

    private void updateRoomType() {
        System.out.println("Chức năng cập nhật thông tin loại phòng!");
        RoomTypeDTO roomType = new RoomTypeDTO();
        System.out.print("Nhập ID loại phòng cần cập nhật: ");
        roomType.setId(scanner.nextLine());
        System.out.print("Nhập tên loại phòng: ");
        roomType.setName(scanner.nextLine());
        System.out.print("Nhập mô tả loại phòng: ");
        roomType.setDescription(scanner.nextLine());

        roomTypeService.updateRoomType(roomType);
    }

    private void deleteRoomType() {
        System.out.println("Chức năng xóa loại phòng!");

        System.out.print("Nhập ID loại phòng cần xóa: ");
        String roomTypeId = scanner.nextLine();
        roomTypeService.deleteRoomType(roomTypeId);
    }
    private void createAsset() {
        List<AssetDTO> assetDTOS = new ArrayList<>();
        System.out.print("Nhập ID loại phòng: ");
        String roomTypeId = scanner.nextLine();
        while (true) {

            AssetDTO  assetDTO = new AssetDTO();
            System.out.print("Nhập tên tài sản: ");
            String assetName = scanner.nextLine();
            if (assetName.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Nhập mô tả tài sản: ");
            assetDTO.setDescription(scanner.nextLine());


            assetDTO.setRoomTypeId(roomTypeId);

            assetDTOS.add(assetDTO);

        }
        for(AssetDTO asset : assetDTOS) {
            assetService.createAsset(asset);
        }
    }

    private void updateAsset() {
        System.out.println("Chức năng cập nhật thông tin tài sản!");
        AssetDTO asset = new AssetDTO();
        System.out.print("Nhập ID tài sản cần cập nhật: ");
        asset.setId(scanner.nextLine());
        System.out.print("Nhập tên tài sản: ");
        asset.setName(scanner.nextLine());
        System.out.print("Nhập mô tả tài sản: ");
        asset.setDescription(scanner.nextLine());

        assetService.updateAsset(asset);
    }

    private void deleteAsset() {
        System.out.println("Chức năng xóa tài sản!");
        System.out.print("Nhập ID tài sản cần xóa: ");
        String assetId = scanner.nextLine();
        assetService.deleteAsset(assetId);
    }




}
