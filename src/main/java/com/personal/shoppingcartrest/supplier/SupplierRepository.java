package com.personal.shoppingcartrest.supplier;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findById(long id);
    List<Supplier> findAllByActiveTrue();
    List<Supplier> findAllByActiveFalse();
    List<Supplier> findAllByActiveTrue(Pageable pageable);
    List<Supplier> findAllByActiveFalse(Pageable pageable);
}
