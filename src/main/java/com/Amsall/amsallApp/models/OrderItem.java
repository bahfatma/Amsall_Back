package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;

    @ManyToOne
    private Color color;

    private Integer quantite;
    private Long prixUnitaire;

    public OrderItem(){};
    public OrderItem(Order order, Product product, Size size, Color color, Integer quantite, Long prixUnitaire) {
        this.order = order;
        product = product;
        this.size = size;
        this.color = color;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Long getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        product = product;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setPrixUnitaire(Long prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
