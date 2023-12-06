package com.taba.nimonaemo.record.model.entity;

import com.taba.nimonaemo.global.base.BaseEntity;
import com.taba.nimonaemo.member.model.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "hair_status")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HairStatus extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "hair_status_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @Column(name = "start_perm_date")
    private LocalDate startPermDate;

    @Column(name = "start_dye_date")
    private LocalDate startDyeDate;

    @Builder
    private HairStatus(@NotNull Member member,
                       LocalDate startPermDate,
                       LocalDate startDyeDate) {
        this.member = member;
        this.startPermDate = startPermDate;
        this.startDyeDate = startDyeDate;
    }

    /**
     * 사용자의 파마 시작 날짜를 변경합니다.
     * @param startPermDate
     */
    public void changeStartPermDate(LocalDate startPermDate) {
        this.startPermDate = startPermDate;
    }

    /**
     * 사용자의 염색 시작 날짜를 변경합니다.
     * @param startDyeDate
     */
    public void changeStartDyeDate(LocalDate startDyeDate) {
        this.startDyeDate = startDyeDate;
    }
}
