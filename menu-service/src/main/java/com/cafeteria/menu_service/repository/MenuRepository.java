package com.cafeteria.menu_service.repository;

import com.cafeteria.menu_service.entity.MenuItem;
import com.cafeteria.menu_service.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByItemName(String itemName);

    List<MenuItem> findByAvailableTrue();

    List<MenuItem> findByCategory(Category category);

    List<MenuItem> findByCategoryAndAvailableTrue(Category category);

    boolean existsByItemName(String itemName);

}