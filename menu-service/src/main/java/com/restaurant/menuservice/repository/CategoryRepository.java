package com.restaurant.menuservice.repository;

import com.restaurant.menuservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByStatus(Integer status);

    boolean existsByName(String name);
}
