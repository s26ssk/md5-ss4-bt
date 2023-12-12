package com.ra.dto.response;

public class ProductResponse {
    private Integer id;
    private String productName;
    private Double productPrice;
    private int categoryId;

    public ProductResponse() {
    }

    public ProductResponse(Integer id, String productName, Double productPrice, int categoryId) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
