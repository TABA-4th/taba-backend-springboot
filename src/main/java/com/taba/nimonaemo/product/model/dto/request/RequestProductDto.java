package com.taba.nimonaemo.product.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class RequestProductDto {

    @NotBlank
    @Schema(description = "건성", example = "1")
    private final Boolean dry;

    @NotBlank
    @Schema(description = "지성", example = "0")
    private final Boolean greasy;

    @NotBlank
    @Schema(description = "민감성", example = "0")
    private final Boolean sensitive;

    @NotBlank
    @Schema(description = "피부염성", example = "0")
    private final Boolean dermatitis;

    @NotBlank
    @Schema(description = "중성", example = "0")
    private final Boolean neutral;

    @NotBlank
    @Schema(description = "탈모성", example = "0")
    private final Boolean loss;
}
