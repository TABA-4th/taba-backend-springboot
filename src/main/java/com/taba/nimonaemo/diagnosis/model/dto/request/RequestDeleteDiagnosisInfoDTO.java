package com.taba.nimonaemo.diagnosis.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class RequestDeleteDiagnosisInfoDTO {
    @NotBlank
    @Schema(description = "닉네임", example = "user1")
    private final String nickname;

    @NotBlank
    @Schema(description = "삭제하고자 하는 구체적인 날짜(제목)", example = "2023-11-23 07:14:05")
    private final String date;
}
