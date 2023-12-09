package com.taba.nimonaemo.diagnosis.model.entity;

import com.taba.nimonaemo.member.model.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosis_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiagnosisResult {
    @Id
    @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @NotNull
    private Member member;

    @NotNull
    @Column(name = "diagnosis_date")
    private LocalDateTime diagnosisDate;

    @NotBlank
    @Lob
    private String imageUrl;

    @NotNull
    @Column(name = "fine_dead_skin_cells", columnDefinition = "NUMBER(2, 1)")
    private double findDeadSkinCells;

    @NotNull
    @Column(name = "excess_sebum", columnDefinition = "NUMBER(2, 1)")
    private double excessSebum;

    @NotNull
    @Column(name = "erythema_between_hair_follicles", columnDefinition = "NUMBER(2)")
    private int erythemaBetweenHairFollicles;

    @NotNull
    @Column(name = "dandruff", columnDefinition = "NUMBER(2)")
    private int dandruff;

    @NotNull
    @Column(name = "hair_loss", columnDefinition = "NUMBER(2)")
    private int hairLoss;

    @NotNull
    @Column(name = "erythema_pustules", columnDefinition = "NUMBER(2)")
    private int erythemaPustules;

    @NotNull
    @Column(name = "top_percentage")
    private String topPercentage;

//    @Builder
//    private DiagnosisResult(@NotNull LocalDateTime diagnosisDate,
//                            @NotNull Member member,
//                            @NotBlank String imageUrl,
//                            @NotNull int findDeadSkinCells,
//                            @NotNull int excessSebum,
//                            @NotNull int erythemaBetweenHairFollicles,
//                            @NotNull int dandruff,
//                            @NotNull int hairLoss,
//                            @NotNull int erythemaPustules) {
//        this.diagnosisDate = diagnosisDate;
//        this.member = member;
//        this.imageUrl = imageUrl;
//        this.findDeadSkinCells = findDeadSkinCells;
//        this.excessSebum = excessSebum;
//        this.erythemaBetweenHairFollicles = erythemaBetweenHairFollicles;
//        this.dandruff = dandruff;
//        this.hairLoss = hairLoss;
//        this.erythemaPustules = erythemaPustules;
//    }
}
