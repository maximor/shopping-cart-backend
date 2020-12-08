package com.personal.shoppingcartrest.category;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(long id);
    List<Category> findAllByActiveTrue();
    List<Category> findAllByActiveFalse();
    List<Category> findAllByActiveTrue(Pageable pageable);
    List<Category> findAllByActiveFalse(Pageable pageable);

}
