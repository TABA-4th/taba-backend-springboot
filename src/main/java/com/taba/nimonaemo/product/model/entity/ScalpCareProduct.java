package com.taba.nimonaemo.product.model.entity;

import com.taba.nimonaemo.global.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "scalp_care_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScalpCareProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @NotNull
    @Column(length = 512)
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    @Lob
    private String imageUrl;

    @NotNull
    @Lob
    private String purchaseUrl;

    @OneToOne(mappedBy = "scalpCareProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductProperty productProperty;

    @Builder
    private ScalpCareProduct(@NotNull String name,
                             @NotNull Integer price,
                             @NotNull String imageUrl,
                             @NotNull String purchaseUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.purchaseUrl = purchaseUrl;
    }
}
