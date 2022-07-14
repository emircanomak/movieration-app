package com.example.filmsitesi.product.entities;

import com.example.filmsitesi.category.entities.Category;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @SequenceGenerator(name="Product",sequenceName = "PRODUCT_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Product")
    private Long id;
    @Column(name = "product_name", length = 100)
    private String productName;
    @Column(name = "product_desc", length = 255)
    private String productDesc;
    @Column(name="product_url")
    private String productUrl;
    @Column(name="product_rate")    //TODO: should it be included precision etc.?
    private double productRate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_category", foreignKey=@ForeignKey(name="FK_PRODUCT_CATEGORY"))
    private Category category;


}
