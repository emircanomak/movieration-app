package com.example.filmsitesi.category.entities;

import com.example.filmsitesi.product.entities.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {


    @Id
    @SequenceGenerator(name="Category", sequenceName = "CATEGORY_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Category")
    Long id;
    @Column(name = "category_name", length = 30)
    String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    List<Product> productList;

}
