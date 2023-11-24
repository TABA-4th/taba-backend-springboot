package com.taba.nimonaemo.product.model.dto.response;

import com.taba.nimonaemo.product.model.entity.ScalpCareProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URLDecoder;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Getter
public class ResponseProductDto {
    private final String name;
    private final Integer price;
    private final String imageUrl;
    private final String purchaseUrl;

    @Builder
    private ResponseProductDto(ScalpCareProduct scalpCareProduct) {
        this.name = scalpCareProduct.getName();
        this.price = scalpCareProduct.getPrice();
        this.imageUrl = scalpCareProduct.getImageUrl();
        this.purchaseUrl = scalpCareProduct.getPurchaseUrl();
    }
}
