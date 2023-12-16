package com.taba.nimonaemo.member.model.entity;

import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import com.taba.nimonaemo.global.auth.role.MemberRole;
import com.taba.nimonaemo.global.base.BaseEntity;
import com.taba.nimonaemo.member.model.MemberStatus;
import com.taba.nimonaemo.record.model.entity.Diary;
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

    public static String DELETED_MEMBER = "탈퇴한 회원";

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

    @OneToMany(mappedBy = "member")
    private List<Diary> diarys = new ArrayList<>();

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


    public String getName() {
        if (!getStatus().isActive()) {
            return DELETED_MEMBER;
        }
        return this.name;
    }

    public String getNickName() {
        if (!getStatus().isActive()) {
            return DELETED_MEMBER;
        }
        return this.nickname;
    }

    /**
     * Member 상태를 변경합니다.
     *
     * @param status 상태
     */
    public void changeStatus(MemberStatus status) {
        this.status = status;
    }

    /**
     * 이름을 변경합니다.
     *
     * @param name 이름
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * 닉네임(아이디)를 변경합니다.
     *
     * @param nickname 닉네임(아이디)
     */
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 탈퇴한 Member의 정보를 수정합니다.
     */
    public void emptyOutUserInfo() {
        this.name = "";
        this.phone = "";
        this.nickname = "";
        this.password = "";
    }
}
