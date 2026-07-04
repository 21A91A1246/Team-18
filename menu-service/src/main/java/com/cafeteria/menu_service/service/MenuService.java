package com.cafeteria.menu_service.service;

import com.cafeteria.menu_service.dto.request.MenuRequestDTO;
import com.cafeteria.menu_service.dto.response.MenuResponseDTO;
import com.cafeteria.menu_service.dto.response.MenuSummaryDTO;
import com.cafeteria.menu_service.enums.Category;

import java.util.List;

public interface MenuService {

    MenuResponseDTO addMenuItem(MenuRequestDTO menuRequestDTO);

    MenuResponseDTO getMenuItemById(Long id);

    List<MenuResponseDTO> getAllMenuItems();

    MenuResponseDTO updateMenuItem(Long id, MenuRequestDTO menuRequestDTO);

    void deleteMenuItem(Long id);

    List<MenuSummaryDTO> getAvailableMenuItems();

    List<MenuSummaryDTO> getMenuItemsByCategory(Category category);

    List<MenuSummaryDTO> getAvailableMenuItemsByCategory(Category category);

}