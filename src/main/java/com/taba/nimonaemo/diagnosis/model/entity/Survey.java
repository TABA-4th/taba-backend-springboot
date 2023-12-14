package com.taba.nimonaemo.diagnosis.model.entity;

import com.taba.nimonaemo.member.model.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_survey")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {
    @Id
    @GeneratedValue
    @Column(name = "survey_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @NotNull
    @Column(name = "survey_date")
    private LocalDateTime surveyDate;

    @NotNull
    @Size(max=16)
    private String gender;

    @NotNull
    @Size(max=16)
    private String old;

    @NotNull
    @Size(max=256)
    private String useAgeTerm;

    @NotNull
    @Size(max=256)
    private String permTerm;

    @NotNull
    @Size(max=256)
    private String dyeTerm;

    @NotNull
    private char recommendOrNot;

    @NotNull
    private char reuseImage;
}
