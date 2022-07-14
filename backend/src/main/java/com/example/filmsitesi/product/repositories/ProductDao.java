package com.example.filmsitesi.product.repositories;

import com.example.filmsitesi.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Long> {
}
