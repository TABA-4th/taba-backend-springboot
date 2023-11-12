package com.taba.nimonaemo.member.model.dto;

import com.taba.nimonaemo.global.auth.role.MemberRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AutoLoginDto {

    private final String userId;
    private final MemberRole memberRole;
}
