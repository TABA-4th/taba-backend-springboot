package com.taba.nimonaemo.global.auth.role;

public class MemberAuthNames {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_MEMBER = "ROLE_MEMBER";

    public static final String ROLE_GUEST = "ROLE_GUEST";

    public static String combine(String... names) {
        return String.join(",", names);
    }
}
