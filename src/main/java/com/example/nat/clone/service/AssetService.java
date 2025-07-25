package com.example.nat.clone.service;


import com.example.nat.clone.model.dto.AssetDTO;
import com.example.nat.clone.model.entity.Asset;
import com.example.nat.clone.model.entity.RoomType;
import com.example.nat.clone.repository.AssetRepository;
import com.example.nat.clone.repository.RoomTypeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createAsset(AssetDTO assetDTO) {
        Asset asset = modelMapper.map(assetDTO , Asset.class);
        if(assetDTO.getRoomTypeId() == null || assetDTO.getRoomTypeId().isEmpty()) {
            throw new IllegalArgumentException("Room Type ID cannot be null or empty");
        }
        else {
            RoomType roomType =  roomTypeRepository.findById(assetDTO.getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Room Type not found "));
            asset.setRoomType(roomType);
        }
        assetRepository.save(asset);

    }
    public void updateAsset( AssetDTO assetDTO) {
        Asset asset = assetRepository.findById(assetDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));

        asset.setName(assetDTO.getName());
        asset.setDescription(assetDTO.getDescription());
        assetRepository.save(asset);
    }

    public void deleteAsset(String id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asset not found"));
        assetRepository.delete(asset);
    }

}
