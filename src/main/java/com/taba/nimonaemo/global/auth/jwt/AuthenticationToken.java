package com.taba.nimonaemo.global.auth.jwt;

public interface AuthenticationToken {
    String getAccessToken();

    String getRefreshToken();
}
