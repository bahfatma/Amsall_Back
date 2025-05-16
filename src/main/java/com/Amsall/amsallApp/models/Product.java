package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<ProductColor> productColors;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

    public Product(){}
    public Product(String name, Category category, Brand brand, BigDecimal price, String description) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    public List<ProductColor> getProductColors() {
        return productColors;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setProductColors(List<ProductColor> productColors) {
        this.productColors = productColors;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }
}
