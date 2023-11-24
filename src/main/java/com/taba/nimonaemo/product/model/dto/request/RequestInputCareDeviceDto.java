package com.taba.nimonaemo.product.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class RequestInputCareDeviceDto {

    @NotBlank
    @Schema(description = "상품 이름", example = "한일의료기 충전식 무선 미니 방수 두피 스켈링 마사지")
    private final String name;

    @NotNull
    @Schema(description = "상품 가격", example = "51000")
    private final Integer price;

    @NotBlank
    @Schema(description = "상품 이미지 URL", example = "https://www.naver.com")
    private final String imageUrl;

    @NotBlank
    @Schema(description = "상품 구매 URL", example = "https://www.naver.com")
    private final String purchaseUrl;

    @NotNull
    @Schema(description = "건성", example = "1")
    private final Boolean dry;

    @NotNull
    @Schema(description = "지성", example = "0")
    private final Boolean greasy;

    @NotNull
    @Schema(description = "민감성", example = "0")
    private final Boolean sensitive;

    @NotNull
    @Schema(description = "피부염성", example = "0")
    private final Boolean dermatitis;

    @NotNull
    @Schema(description = "중성", example = "0")
    private final Boolean neutral;

    @NotNull
    @Schema(description = "탈모성", example = "0")
    private final Boolean loss;

    @NotNull
    @Schema(description = "두피 케어 기기", example = "0")
    private final Boolean careDevice;
}
