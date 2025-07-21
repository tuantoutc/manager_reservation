package com.example.nat.clone.service.impl;

import com.example.nat.clone.exception.AppException;
import com.example.nat.clone.exception.ErrorCode;
import com.example.nat.clone.model.dto.UserDTO;
import com.example.nat.clone.model.entity.Hotel;
import com.example.nat.clone.model.entity.User;
import com.example.nat.clone.model.request.UserRequest;
import com.example.nat.clone.repository.UserRepository;
import com.example.nat.clone.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public User createUser(User user) {
        if(userRepository.existsByName(user.getName())) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        return userRepository.save(user);
    }


    @Override
    public UserDTO updatedUser(UserDTO request) {
        User userEntity = userRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String username = userEntity.getName();
        userEntity = modelMapper.map(request, User.class);
        userEntity.setName(username);
        return modelMapper.map(userRepository.save(userEntity), UserDTO.class) ;
    }

    @Override
    public User updateUser(UserDTO request) {
        User userEntity = userRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String username = userEntity.getName();
        userEntity = modelMapper.map(request, User.class);
        userEntity.setName(username);
        return userRepository.save(userEntity);
    }

    @Override
    public String deleteUser(List<String> ids) {
            if(userRepository.existsByIdIn(ids) )
            {
                userRepository.deleteByIdIn(ids);
                return "Deleted successfully";
            }
            else {
                return "Some ids do not exist, please check and try again";
                }
    }
    @Override
    public UserDTO createdUser(UserRequest user) {

        if(userRepository.existsByName(user.getName())) {
            throw new RuntimeException("Username already exists");
        }

        User userEntity =  User.builder()
                                        .name(user.getName())
                                        .email(user.getEmail())
                                        .phone(user.getPhone())
                                        .address(user.getAddress())
                                        .dob(user.getDob())
                                        .build();
        return modelMapper.map(userRepository.save(userEntity), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .toList();
    }
    @Override
    public UserDTO getUserById(String id) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public void displayAllUser() {
        List<UserDTO> users = getAllUsers();
        System.out.println("\n=== DANH SÁCH KHÁCH HÀNG ===");
        System.out.println("ID --------------------- Name ----------------- Email ----------------- Phone ----------------- Address ----------------- DOB");
        users.forEach(user -> {
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
