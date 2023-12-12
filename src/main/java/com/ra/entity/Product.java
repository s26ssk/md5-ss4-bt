package com.ra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private Double productPrice;
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    @JsonIgnore
    private Category category;

    public Product() {
    }

    public Product(int id, String productName, Double productPrice, Category category) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
