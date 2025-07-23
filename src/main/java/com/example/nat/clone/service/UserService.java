package com.example.nat.clone.service;

import com.example.nat.clone.model.dto.UserDTO;
import com.example.nat.clone.model.request.UserRequest;
import com.example.nat.clone.model.entity.User;
import com.example.nat.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface UserService {

    public List<UserDTO> getAllUsers();

    public UserDTO getUserById(String id);

    public UserDTO createdUser(UserRequest user);


    public String deleteUser(String id);

    public UserDTO updatedUser(UserDTO request);

    public User createUser(User user);

    public void displayAllUser();
}
