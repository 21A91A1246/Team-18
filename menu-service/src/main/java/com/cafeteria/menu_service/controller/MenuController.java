package com.cafeteria.menu_service.controller;

import com.cafeteria.menu_service.dto.request.MenuRequestDTO;
import com.cafeteria.menu_service.dto.response.MenuResponseDTO;
import com.cafeteria.menu_service.dto.response.MenuSummaryDTO;
import com.cafeteria.menu_service.enums.Category;
import com.cafeteria.menu_service.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Tag(name = "Menu Controller", description = "APIs for Menu Management")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "Add a new menu item")
    @PostMapping
    public ResponseEntity<MenuResponseDTO> addMenuItem(
            @Valid @RequestBody MenuRequestDTO menuRequestDTO) {

        return new ResponseEntity<>(
                menuService.addMenuItem(menuRequestDTO),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Get menu item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MenuResponseDTO> getMenuItemById(
            @PathVariable Long id) {

        return ResponseEntity.ok(menuService.getMenuItemById(id));
    }

    @Operation(summary = "Get all menu items")
    @GetMapping
    public ResponseEntity<List<MenuResponseDTO>> getAllMenuItems() {

        return ResponseEntity.ok(menuService.getAllMenuItems());
    }

    @Operation(summary = "Update menu item")
    @PutMapping("/{id}")
    public ResponseEntity<MenuResponseDTO> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuRequestDTO menuRequestDTO) {

        return ResponseEntity.ok(
                menuService.updateMenuItem(id, menuRequestDTO));
    }

    @Operation(summary = "Delete menu item")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(
            @PathVariable Long id) {

        menuService.deleteMenuItem(id);

        return ResponseEntity.ok("Menu item deleted successfully.");
    }

    @Operation(summary = "Get all available menu items")
    @GetMapping("/available")
    public ResponseEntity<List<MenuSummaryDTO>> getAvailableMenuItems() {

        return ResponseEntity.ok(menuService.getAvailableMenuItems());
    }

    @Operation(summary = "Get menu items by category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuSummaryDTO>> getMenuItemsByCategory(
            @PathVariable Category category) {

        return ResponseEntity.ok(
                menuService.getMenuItemsByCategory(category));
    }

    @Operation(summary = "Get available menu items by category")
    @GetMapping("/category/{category}/available")
    public ResponseEntity<List<MenuSummaryDTO>> getAvailableMenuItemsByCategory(
            @PathVariable Category category) {

        return ResponseEntity.ok(
                menuService.getAvailableMenuItemsByCategory(category));
    }

}