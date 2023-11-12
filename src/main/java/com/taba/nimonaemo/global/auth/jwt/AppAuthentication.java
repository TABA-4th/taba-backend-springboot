package com.taba.nimonaemo.global.auth.jwt;

import com.taba.nimonaemo.global.auth.role.MemberRole;
import org.springframework.security.core.Authentication;

public interface AppAuthentication extends Authentication {
    Long getUserId();

    MemberRole getUserRole();

    boolean isAdmin();
}
