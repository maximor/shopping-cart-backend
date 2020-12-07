package com.personal.shoppingcartrest.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
    List<Product> findAllByCategoryId(long categoryId);
    List<Product> findAllByBrandId(long brandId);
    List<Product> findAllByCategoryIdAndBrandId(long categoryId, long brandId);
}
