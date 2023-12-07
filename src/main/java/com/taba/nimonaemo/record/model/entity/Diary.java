package com.taba.nimonaemo.record.model.entity;

import com.taba.nimonaemo.member.model.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "diary")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @NotNull
    @Column(name = "diary_date")
    private LocalDate diaryDate;

    @NotBlank
    @Lob
    private String content;

    @Builder
    private Diary(@NotNull Member member,
                  @NotNull LocalDate diaryDate,
                  @NotNull String content) {
        this.member = member;
        this.diaryDate = diaryDate;
        this.content = content;
    }
}
