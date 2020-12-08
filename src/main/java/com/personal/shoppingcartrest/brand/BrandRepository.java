package com.personal.shoppingcartrest.brand;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findById(long id);
    List<Brand> findAllByActiveTrue();
    List<Brand> findAllByActiveFalse();
    List<Brand> findAllByActiveTrue(Pageable pageable);
    List<Brand> findAllByActiveFalse(Pageable pageable);
}
