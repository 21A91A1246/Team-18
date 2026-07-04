package com.cafeteria.menu_service.dto.response;

import com.cafeteria.menu_service.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuSummaryDTO {

    private Long id;

    private String itemName;

    private BigDecimal price;

    private Boolean available;

    private Category category;

}