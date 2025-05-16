package com.Amsall.amsallApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    public Category(){}

    public Category(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getArticlesList() {
        return productList;
    }

    public void setArticlesList(List<Product> productList) {
        this.productList = productList;
    }

}
