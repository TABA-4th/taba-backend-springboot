package com.taba.nimonaemo.product.repository;

import com.taba.nimonaemo.product.model.entity.ScalpCareProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScalpCareProductRepository extends JpaRepository<ScalpCareProduct, Long> {
    @Query("select s from ScalpCareProduct s where s.name = :name")
    Optional<ScalpCareProduct> findByName(String name);
}
