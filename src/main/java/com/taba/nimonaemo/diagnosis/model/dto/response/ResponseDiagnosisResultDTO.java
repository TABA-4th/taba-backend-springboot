package com.taba.nimonaemo.diagnosis.model.dto.response;

import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ResponseDetailDiagnosisResultDTO {
    private final LocalDateTime diagnosisDate;
    private final String imageUrl;
    private final double findDeadSkinCells;
    private final double excessSebum;
    private final int erythemaBetweenHairFollicles;
    private final int dandruff;
    private final int hairLoss;
    private final int erythemaPustules;
    private final String gender;
    private final String old;
    private final double avgFindDeadSkinCells;
    private final double avgExcessSebum;
    private final double avgErythemaBetweenHairFollicles;
    private final double avgDandruff;
    private final double avgHairLoss;
    private final double avgErythemaPustules;

    @Builder
    private ResponseDetailDiagnosisResultDTO(DiagnosisResult diagnosisResult,
                                             ResponseAverageByAgeDTO dto) {
        this.diagnosisDate = diagnosisResult.getDiagnosisDate();
        this.imageUrl = diagnosisResult.getImageUrl();
        this.findDeadSkinCells = diagnosisResult.getFindDeadSkinCells();
        this.excessSebum = diagnosisResult.getExcessSebum();
        this.erythemaBetweenHairFollicles = diagnosisResult.getErythemaBetweenHairFollicles();
        this.dandruff = diagnosisResult.getDandruff();
        this.hairLoss = diagnosisResult.getHairLoss();
        this.erythemaPustules = diagnosisResult.getErythemaPustules();
        this.gender = dto.getGender();
        this.old = dto.getOld();
        this.avgFindDeadSkinCells = dto.getAvgFindDeadSkinCells();
        this.avgExcessSebum = dto.getAvgExcessSebum();
        this.avgErythemaBetweenHairFollicles = dto.getAvgErythemaBetweenHairFollicles();
        this.avgDandruff = dto.getAvgDandruff();
        this.avgHairLoss = dto.getAvgHairLoss();
        this.avgErythemaPustules = dto.getAvgErythemaPustules();
    }
}
