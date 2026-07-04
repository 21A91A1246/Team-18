package com.cafeteria.menu_service.dto.response;

import com.cafeteria.menu_service.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDTO {

    private Long id;

    private String itemName;

    private Category category;

    private String description;

    private BigDecimal price;

    private Boolean available;

    private String imageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}