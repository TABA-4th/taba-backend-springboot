package com.taba.nimonaemo.product.model.entity;


import com.taba.nimonaemo.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_property")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductProperty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_property_id")
    private Long id;
    
    // 건성
    @NotNull
    private Boolean dry;

    // 지성
    @NotNull
    private Boolean greasy;

    // 민감성
    @NotNull
    private Boolean sensitive;

    // 피부염성
    @NotNull
    private Boolean dermatitis;

    // 중성
    @NotNull
    private Boolean neutral;

    // 탈모성
    @NotNull
    private Boolean loss;

    // 두피 케어 기기
    @NotNull
    private Boolean careDevice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ScalpCareProduct scalpCareProduct;

    @Builder
    private ProductProperty(@NotNull Boolean dry,
                            @NotNull Boolean greasy,
                            @NotNull Boolean sensitive,
                            @NotNull Boolean dermatitis,
                            @NotNull Boolean neutral,
                            @NotNull Boolean loss,
                            @NotNull Boolean careDevice,
                            @NotNull ScalpCareProduct scalpCareProduct) {
        this.dry = dry;
        this.greasy = greasy;
        this.sensitive = sensitive;
        this.dermatitis = dermatitis;
        this.neutral = neutral;
        this.loss = loss;
        this.careDevice = careDevice;
        this.scalpCareProduct = scalpCareProduct;
    }
}
