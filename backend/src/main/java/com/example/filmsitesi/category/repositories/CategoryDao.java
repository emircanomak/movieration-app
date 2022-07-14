package com.example.filmsitesi.category.repositories;

import com.example.filmsitesi.category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {
}
