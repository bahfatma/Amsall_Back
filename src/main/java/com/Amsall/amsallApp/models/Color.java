package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long id;

    @Column(nullable = false,unique = true)
    private String name;

    public Color(){};
    public Color(String name){
        this.name = name;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
