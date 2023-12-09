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
    private final String topPercentage;

    @Builder
    private ResponseDetailDiagnosisResultDTO(DiagnosisResult diagnosisResult,
                                             ResponseAverageByAgeDTO responseAverageByAgeDTO) {
        this.diagnosisDate = diagnosisResult.getDiagnosisDate();
        this.imageUrl = diagnosisResult.getImageUrl();
        this.findDeadSkinCells = diagnosisResult.getFindDeadSkinCells();
        this.excessSebum = diagnosisResult.getExcessSebum();
        this.erythemaBetweenHairFollicles = diagnosisResult.getErythemaBetweenHairFollicles();
        this.dandruff = diagnosisResult.getDandruff();
        this.hairLoss = diagnosisResult.getHairLoss();
        this.erythemaPustules = diagnosisResult.getErythemaPustules();
        this.topPercentage = diagnosisResult.getTopPercentage();
        this.gender = responseAverageByAgeDTO.getGender();
        this.old = responseAverageByAgeDTO.getOld();
        this.avgFindDeadSkinCells = responseAverageByAgeDTO.getAvgFindDeadSkinCells();
        this.avgExcessSebum = responseAverageByAgeDTO.getAvgExcessSebum();
        this.avgErythemaBetweenHairFollicles = responseAverageByAgeDTO.getAvgErythemaBetweenHairFollicles();
        this.avgDandruff = responseAverageByAgeDTO.getAvgDandruff();
        this.avgHairLoss = responseAverageByAgeDTO.getAvgHairLoss();
        this.avgErythemaPustules = responseAverageByAgeDTO.getAvgErythemaPustules();
    }
}
