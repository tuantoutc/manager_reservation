package com.example.nat.clone.controller;


import com.example.nat.clone.model.dto.ApiResponse;
import com.example.nat.clone.model.dto.UserDTO;
import com.example.nat.clone.model.request.UserRequest;
import com.example.nat.clone.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        if (userService.getAllUsers().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping()
    public ApiResponse<UserDTO> createUser(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<UserDTO>builder()
                .code(200)
                .data(userService.createdUser(request))
                .build();
    }

    @PatchMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO request) {
        return ResponseEntity.ok().body(userService.updatedUser(request));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@RequestParam(value = "ids") String ids) {
        return ResponseEntity.ok().body(userService.deleteUser(List.of(ids.split(","))));
    }


}
