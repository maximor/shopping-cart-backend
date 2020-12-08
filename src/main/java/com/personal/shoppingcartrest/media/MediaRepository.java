package com.personal.shoppingcartrest.media;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findById(long id);
    List<Media> findAllByProductId(long productId);
}
