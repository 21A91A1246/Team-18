package com.cafeteria.menu_service.mapper;

import com.cafeteria.menu_service.dto.request.MenuRequestDTO;
import com.cafeteria.menu_service.dto.response.MenuResponseDTO;
import com.cafeteria.menu_service.dto.response.MenuSummaryDTO;
import com.cafeteria.menu_service.entity.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public MenuItem toEntity(MenuRequestDTO requestDTO) {

        return MenuItem.builder()
                .itemName(requestDTO.getItemName())
                .category(requestDTO.getCategory())
                .description(requestDTO.getDescription())
                .price(requestDTO.getPrice())
                .available(requestDTO.getAvailable())
                .imageUrl(requestDTO.getImageUrl())
                .build();
    }

    public MenuResponseDTO toResponseDTO(MenuItem menuItem) {

        return MenuResponseDTO.builder()
                .id(menuItem.getId())
                .itemName(menuItem.getItemName())
                .category(menuItem.getCategory())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .available(menuItem.getAvailable())
                .imageUrl(menuItem.getImageUrl())
                .createdAt(menuItem.getCreatedAt())
                .updatedAt(menuItem.getUpdatedAt())
                .build();
    }

    public MenuSummaryDTO toSummaryDTO(MenuItem menuItem) {

        return MenuSummaryDTO.builder()
                .id(menuItem.getId())
                .itemName(menuItem.getItemName())
                .price(menuItem.getPrice())
                .available(menuItem.getAvailable())
                .category(menuItem.getCategory())
                .build();
    }

    public void updateEntity(MenuRequestDTO requestDTO, MenuItem menuItem) {

        menuItem.setItemName(requestDTO.getItemName());
        menuItem.setCategory(requestDTO.getCategory());
        menuItem.setDescription(requestDTO.getDescription());
        menuItem.setPrice(requestDTO.getPrice());
        menuItem.setAvailable(requestDTO.getAvailable());
        menuItem.setImageUrl(requestDTO.getImageUrl());
    }

}