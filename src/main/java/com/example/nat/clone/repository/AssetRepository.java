package com.example.nat.clone.repository;

import com.example.nat.clone.model.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, String> {
    // Additional query methods can be defined here if needed
}
