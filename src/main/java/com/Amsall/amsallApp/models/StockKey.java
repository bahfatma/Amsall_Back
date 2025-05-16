package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockKey implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "size_id")
    private Long sizeId;

    public StockKey() {}
    public StockKey(Long productId, Long sizeId) {
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockKey)) return false;
        StockKey that = (StockKey) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(sizeId, that.sizeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sizeId);
    }
}
