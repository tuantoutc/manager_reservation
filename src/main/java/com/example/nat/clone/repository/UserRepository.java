package com.example.nat.clone.repository;

import com.example.nat.clone.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

    void deleteByIdIn(List<String> ids);
    boolean existsByIdIn(List<String>ids);
    boolean existsByName(String name);

    User findByNameLikeAndPhone(String name, String phone);
}
