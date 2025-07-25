package com.example.nat.clone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(404, "User not found"),
    ROOM_NOT_FOUND(404, "Room not found"),
    ROOM_TYPE_NOT_FOUND(404, "Room type not found"),
    HOTEL_NOT_FOUND(404, "Hotel not found"),
    RESERVATION_NOT_FOUND(404, "Reservation not found"),
    USER_ALREADY_EXISTS(409, "User already exists"),
    PASSWORD_INVALID(400, "Password is invalid"),
    USER_INVALID(400, "User is invalid"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    BAD_REQUEST(400, "Bad request"),
    UNAUTHORIZED(401, "Unauthorized"),
    UNAUTHENTICATED(401, "Unauthenticated"),
    FORBIDDEN(403, "Forbidden"),
    RESOURCE_NOT_FOUND(404, "Resource not found"),
    METHOD_NOT_ALLOWED(405, "Method not allowed"),
    CONFLICT(409, "Conflict"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable entity"),
    SERVICE_UNAVAILABLE(503, "Service unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway timeout"),
    INVALID_KEY(401, "Invalid error"),
    EMAIL_INVALID(400, "Email is invalid"),
    Time_INVALID(400, "Time is invalid");

    private int code;
    private String message;
}
