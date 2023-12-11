package com.taba.nimonaemo.member.model.dto.response;

import com.taba.nimonaemo.global.auth.jwt.AuthenticationToken;
import lombok.Getter;

@Getter
public class ResponseRefreshTokenDto {
    private final String accessToken;
    private final String refreshToken;

    public ResponseRefreshTokenDto(AuthenticationToken token) {
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
    }
}
