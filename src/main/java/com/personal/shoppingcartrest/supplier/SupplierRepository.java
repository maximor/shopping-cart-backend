package com.personal.shoppingcartrest.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findById(long id);
}
