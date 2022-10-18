package com.example.chuchu.category.repository;

import com.example.chuchu.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
