package com.taba.nimonaemo.diagnosis.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class RequestDetailMemberDTO {

    @NotBlank
    @Schema(description = "닉네임", example = "user1")
    private final String nickname;

    @NotBlank
    @Schema(description = "찾고자 하는 정확한 날짜", example = "2023-12-07 14:10:36")
    private final String date;
}
