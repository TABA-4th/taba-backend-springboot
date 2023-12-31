package com.taba.nimonaemo.member.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class RequestPhoneNumberDto {

    @NotBlank
    @Pattern(regexp = "01[0-1|6-9]-?\\d{4}-?\\d{4}")
    @Schema(description = "휴대폰 번호", example = "01011112222")
    private String phone;

    public RequestPhoneNumberDto() {
    }

    public RequestPhoneNumberDto(String phone) {
        this.phone = phone;
    }
}
