package com.ra.controller;


import com.ra.dto.request.ProductRequest;
import com.ra.dto.response.ProductResponse;
import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> productList = productService.fillAll();
        List<ProductResponse> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setProductName(product.getProductName());
            productResponse.setProductPrice(product.getProductPrice());
            productResponse.setCategoryId(product.getCategory().getId());

            productDTOList.add(productResponse);
        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Integer id){
        Product product = productService.findById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setCategoryId(product.getCategory().getId());
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest){
        String productName = productRequest.getProductName();
        Double productPrice = productRequest.getProductPrice();
        int categoryId = productRequest.getCategoryId();

        Category category = categoryService.findById(categoryId);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setCategory(category);

        Product productNew = productService.save(product);
        if(productNew == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(productNew, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductResponse productResponse, @PathVariable("id") Integer id) {
        Product product = productService.findById(id);

        if (product != null) {
            product.setProductName(productResponse.getProductName());
            product.setProductPrice(productResponse.getProductPrice());
            product.setCategory(categoryService.findById(productResponse.getCategoryId()));

            Product updatedProduct = productService.save(product);

            ProductResponse productResponseNew = new ProductResponse();
            productResponseNew.setId(updatedProduct.getId());
            productResponseNew.setProductName(updatedProduct.getProductName());
            productResponseNew.setProductPrice(updatedProduct.getProductPrice());
            productResponseNew.setCategoryId(updatedProduct.getCategory().getId());

            return new ResponseEntity<>(productResponseNew, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        if (productService.findById(id) != null){
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
