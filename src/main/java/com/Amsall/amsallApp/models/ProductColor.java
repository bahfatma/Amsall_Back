package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_colors")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    public ProductColor(){};
    public ProductColor(Product product, Color color) {
        this.product = product;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
