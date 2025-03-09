package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.dao.entity.ProductEntity;

public abstract class ProductService {
    public abstract List<ProductEntity> getAllProducts();
    public abstract ProductEntity addProduct(ProductEntity product);
    public abstract ProductEntity updateProduct(ProductEntity product);
    public abstract void deleteProduct(Long idPk);
    public abstract ProductEntity getProductById(Long idPk);
}