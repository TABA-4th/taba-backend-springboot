package com.taba.nimonaemo.product.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class RequestProductDto {

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
}
