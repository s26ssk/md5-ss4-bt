package com.ra.service;

import com.ra.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> fillAll();
    Product findById(Integer id);
    Product save(Product product);
    void deleteById(Integer id);
}
