package com.Amsall.amsallApp.models;
import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @EmbeddedId
    private StockKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne @MapsId("sizeId")
    @JoinColumn(name = "size_id")
    private Size size;

    private Integer quantity;

    public Stock(){};
    public Stock(Product product, Size size, Integer quantity) {
        this.product = product;
        this.size = size;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Size getSize() {
        return size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
