package com.taba.nimonaemo.member.model.entity;

import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import com.taba.nimonaemo.global.auth.role.MemberRole;
import com.taba.nimonaemo.global.base.BaseEntity;
import com.taba.nimonaemo.member.model.MemberStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Column(length = 20)
    private String name;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @NotNull
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<DiagnosisResult> diagnosisResults = new ArrayList<>();

    @Builder
    private Member(@NotNull String name,
                   @NotNull String nickname,
                   @NotNull String password,
                   @NotNull String phone,
                   MemberRole memberRole,
                   MemberStatus status) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.memberRole = memberRole;
        this.status = status;
    }
}
