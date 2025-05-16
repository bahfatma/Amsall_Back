package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long id;

    @Column(nullable = false,unique = true)
    private String name;

    public Brand(){};
    public Brand(String name) {
        this.name = name;
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
}
