package com.taba.nimonaemo.member.model.dto.response;

import com.taba.nimonaemo.global.auth.jwt.AuthenticationToken;
import com.taba.nimonaemo.member.model.entity.Member;
import lombok.Getter;

@Getter
public class ResponseLoginDto {
    private final String accessToken;
    private final String refreshToken;
    private final String name;
    private final String phone;
    private final boolean isAdmin;

    public ResponseLoginDto(AuthenticationToken token, Member member) {
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.isAdmin = member.getMemberRole().isAdmin();
    }
}
