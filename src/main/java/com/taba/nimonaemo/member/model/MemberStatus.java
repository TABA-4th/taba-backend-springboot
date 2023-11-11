package com.taba.nimonaemo.member.model;

public enum MemberStatus {
    /**
     * 활성화 계정
     */
    ACTIVE,

    /**
     * 비활성화 계정
     */
    INACTIVE;

    public boolean isActive() {
        return this == ACTIVE;
    }
}
