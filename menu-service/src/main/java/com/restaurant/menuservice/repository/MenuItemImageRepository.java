package com.restaurant.menuservice.repository;

import com.restaurant.menuservice.entity.MenuItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemImageRepository extends JpaRepository<MenuItemImage, Long> {

    List<MenuItemImage> findByMenuItem_MenuItemId(Long menuItemId);

    List<MenuItemImage> findByStatus(Integer status);
}
