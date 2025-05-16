package com.Amsall.amsallApp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SizeType type;

    public Size(){};
    public Size(String name, SizeType type) {
        this.name = name;
        this.type = type;
    }

    public void setType(SizeType type) {
        this.type = type;
    }

    public SizeType getType() {
        return type;
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

}
