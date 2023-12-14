package com.taba.nimonaemo.global.auth.role;

import com.taba.nimonaemo.global.auth.jwt.AppAuthentication;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.taba.nimonaemo.global.auth.role.MemberAuthNames.*;

@Getter
public enum MemberRole {
    MEMBER(ROLE_MEMBER),
    GUEST(ROLE_GUEST),
    ADMIN(combine(ROLE_ADMIN, ROLE_MEMBER));

    private final String name;

    MemberRole(String name){
        this.name = name;
    }

    private static final Map<String, MemberRole> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(MemberRole::getName, e -> e));

    public static MemberRole of(String name) {
        return BY_LABEL.get(name);
    }

    public static MemberRole from(AppAuthentication auth) {
        if (auth == null) {
            return GUEST;
        }
        return auth.getUserRole();
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }
}
