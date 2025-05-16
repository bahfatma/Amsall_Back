package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(){};
    public ProductImage(String url, Product product) {
        this.url = url;
        this.product = product;
    }

    public String getUrl() {
        return url;
    }

    public Product getProduct() {
        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
