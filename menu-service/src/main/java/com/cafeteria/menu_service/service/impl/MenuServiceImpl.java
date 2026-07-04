package com.cafeteria.menu_service.service.impl;

import com.cafeteria.menu_service.dto.request.MenuRequestDTO;
import com.cafeteria.menu_service.dto.response.MenuResponseDTO;
import com.cafeteria.menu_service.dto.response.MenuSummaryDTO;
import com.cafeteria.menu_service.entity.MenuItem;
import com.cafeteria.menu_service.enums.Category;
import com.cafeteria.menu_service.exception.DuplicateResourceException;
import com.cafeteria.menu_service.exception.ResourceNotFoundException;
import com.cafeteria.menu_service.mapper.MenuMapper;
import com.cafeteria.menu_service.repository.MenuRepository;
import com.cafeteria.menu_service.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
    public MenuResponseDTO addMenuItem(MenuRequestDTO menuRequestDTO) {

        if (menuRepository.existsByItemName(menuRequestDTO.getItemName())) {
            throw new DuplicateResourceException("Menu item already exists with name: "
                    + menuRequestDTO.getItemName());
        }

        MenuItem menuItem = menuMapper.toEntity(menuRequestDTO);

        return menuMapper.toResponseDTO(menuRepository.save(menuItem));
    }

    @Override
    public MenuResponseDTO getMenuItemById(Long id) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found with id: " + id));

        return menuMapper.toResponseDTO(menuItem);
    }

    @Override
    public List<MenuResponseDTO> getAllMenuItems() {

        return menuRepository.findAll()
                .stream()
                .map(menuMapper::toResponseDTO)
                .toList();
    }

    @Override
    public MenuResponseDTO updateMenuItem(Long id, MenuRequestDTO menuRequestDTO) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found with id: " + id));

        if (!menuItem.getItemName().equalsIgnoreCase(menuRequestDTO.getItemName())
                && menuRepository.existsByItemName(menuRequestDTO.getItemName())) {

            throw new DuplicateResourceException("Menu item already exists with name: "
                    + menuRequestDTO.getItemName());
        }

        menuMapper.updateEntity(menuRequestDTO, menuItem);

        return menuMapper.toResponseDTO(menuRepository.save(menuItem));
    }

    @Override
    public void deleteMenuItem(Long id) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Menu item not found with id: " + id));

        menuRepository.delete(menuItem);
    }

    @Override
    public List<MenuSummaryDTO> getAvailableMenuItems() {

        return menuRepository.findByAvailableTrue()
                .stream()
                .map(menuMapper::toSummaryDTO)
                .toList();
    }

    @Override
    public List<MenuSummaryDTO> getMenuItemsByCategory(Category category) {

        return menuRepository.findByCategory(category)
                .stream()
                .map(menuMapper::toSummaryDTO)
                .toList();
    }

    @Override
    public List<MenuSummaryDTO> getAvailableMenuItemsByCategory(Category category) {

        return menuRepository.findByCategoryAndAvailableTrue(category)
                .stream()
                .map(menuMapper::toSummaryDTO)
                .toList();
    }

}