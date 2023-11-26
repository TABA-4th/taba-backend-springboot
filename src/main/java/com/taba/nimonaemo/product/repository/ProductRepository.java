package com.taba.nimonaemo.product.repository;

import com.taba.nimonaemo.product.exception.ProductNotFoundException;
import com.taba.nimonaemo.product.model.dto.request.RequestProductDto;
import com.taba.nimonaemo.product.model.entity.ScalpCareProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;

    /**
     *  TODO : 추후 QueryDSL로 변경하기..
     */
    public List<ScalpCareProduct> findAllByShampooType(RequestProductDto dto) {
        String jpql = "select s from ScalpCareProduct s " +
                        "where s.id in (select p.scalpCareProduct.id from ProductProperty p ";
        Boolean isFirstCondition = true;

        if (dto.getDry() == true) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            }
            else {
                jpql += " and";
            }
            jpql += " p.dry = 1";
        }

        if (dto.getGreasy() == true) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            }
            else {
                jpql += " and";
            }
            jpql += " p.greasy = 1";
        }

        if (dto.getSensitive() == true) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            }
            else {
                jpql += " and";
            }
            jpql += " p.sensitive = 1";
        }

        if (dto.getDermatitis() == true) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            }
            else {
                jpql += " and";
            }
            jpql += " p.dermatitis = 1";
        }

        if (dto.getNeutral() == true) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            }
            else {
                jpql += " and";
            }
            jpql += " p.neutral = 1";
        }

        if (dto.getLoss() == true) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            }
            else {
                jpql += " and";
            }
            jpql += " p.loss = 1";
        }

        jpql += ")";

        if (isFirstCondition) {
            throw new ProductNotFoundException();
        }

        TypedQuery<ScalpCareProduct> query = em.createQuery(jpql, ScalpCareProduct.class);

        return query.getResultList();
    }
}
