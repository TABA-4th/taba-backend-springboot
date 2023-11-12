package com.taba.nimonaemo.member.model.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@RequiredArgsConstructor
public class RequestReissueDto {

    @NotBlank
    private final String refreshToken;

}
