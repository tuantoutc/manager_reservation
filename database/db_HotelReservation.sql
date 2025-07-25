create database managermentreservation;
use managermentreservation;

CREATE TABLE user (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    dob DATE,
    phone VARCHAR(30) UNIQUE,
    email VARCHAR(320) UNIQUE,
    address NVARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE hotel (
    id VARCHAR(255)  PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    address TEXT NOT NULL,
    number_of_room INT NOT NULL CHECK (number_of_room >= 0),
    manager_name VARCHAR(200),
    manager_phone VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE room_type (
    id VARCHAR(255)  PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    hotel_id VARCHAR(255) ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (hotel_id) REFERENCES hotel(id) ON DELETE CASCADE

);

CREATE TABLE room (
    id VARCHAR(255)  PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    hotel_id VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'available',
    price DECIMAL(12,2) NOT NULL CHECK (price >= 0),
    room_type_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_room_hotel_name (hotel_id, name),
    FOREIGN KEY (hotel_id) REFERENCES hotel(id) ON DELETE CASCADE,
    FOREIGN KEY (room_type_id) REFERENCES room_type(id) ON DELETE RESTRICT
);

CREATE TABLE asset (
    id VARCHAR(255)  PRIMARY KEY,
    room_id VARCHAR(255) NOT NULL,
    name VARCHAR(200) NOT NULL,
	description TEXT,
    room_type_id VARCHAR(255) ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE,
    FOREIGN KEY (room_type_id) REFERENCES room_type(id) ON DELETE CASCADE
    
);

CREATE TABLE reservation (
    id VARCHAR(255)  PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    room_id VARCHAR(255) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    reservation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(255) NOT NULL DEFAULT 'PENDING',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CHECK (end_time > start_time),
    UNIQUE KEY uq_reservation_user_room_time (user_id, room_id, start_time, end_time),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
);
DELIMITER //

CREATE TRIGGER check_room_booking_conflict
BEFORE INSERT ON reservation
FOR EACH ROW
BEGIN
    DECLARE conflict_count INT;

    SELECT COUNT(*) INTO conflict_count
    FROM reservation
    WHERE room_id = NEW.room_id
      AND status IN ('pending', 'confirmed', 'checked_in') -- chỉ kiểm tra các đơn còn hiệu lực
      AND (
          NEW.start_time < end_time
          AND NEW.end_time > start_time
      );

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Thời gian đặt phòng bị trùng với một đặt chỗ khác.';
    END IF;
END;
//

DELIMITER ;

