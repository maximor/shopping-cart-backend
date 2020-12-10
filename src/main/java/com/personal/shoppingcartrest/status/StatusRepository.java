package com.personal.shoppingcartrest.status;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findById(long id);
    List<Status> findAllByActiveTrue();
    List<Status> findAllByActiveFalse();
}
