package com.taba.nimonaemo.product.repository;

import com.taba.nimonaemo.product.model.entity.ProductProperty;
import com.taba.nimonaemo.product.model.entity.ScalpCareProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Long> {

//    @Query("select s from ScalpCareProduct s" +
//            "where s.product_id = (select p.product_property_id from ProductProperty p" +
//                                    "where p.dry = :#{#type.dry} and " +
//                                            "p.greasy = :#{#type.greasy} and " +
//            "p.sensitive = :#{#type.sensitive} and " +
//            "p.dermatitis = :#{#type.dermatitis} and " +
//            "p.neutral = :#{#type.neutral} and " +
//            "p.loss = :#{#type.loss} and" +
//            "p.care"
//            ")")
//    Optional<ScalpCareProduct> findByType(@Param("type") RequestProductDto dto);

    @Query("select s from ScalpCareProduct s " +
            "where s.id in (select p.scalpCareProduct.id from ProductProperty p " +
            "where p.careDevice = 1)")
    List<ScalpCareProduct> findByCareDevice();
}