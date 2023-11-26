package com.taba.nimonaemo.diagnosis.model.dto.response;

import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ResponseDiagnosisResultDTO {
    private final LocalDateTime diagnosisDate;
    private final String imageUrl;
    private final int findDeadSkinCells;
    private final int excessSebum;
    private final int erythemaBetweenHairFollicles;
    private final int dandruff;
    private final int hairLoss;
    private final int erythemaPustules;

    @Builder
    private ResponseDiagnosisResultDTO(DiagnosisResult diagnosisResult) {
        this.diagnosisDate = diagnosisResult.getDiagnosisDate();
        this.imageUrl = diagnosisResult.getImageUrl();
        this.findDeadSkinCells = diagnosisResult.getFindDeadSkinCells();
        this.excessSebum = diagnosisResult.getExcessSebum();
        this.erythemaBetweenHairFollicles = diagnosisResult.getErythemaBetweenHairFollicles();
        this.dandruff = diagnosisResult.getDandruff();
        this.hairLoss = diagnosisResult.getHairLoss();
        this.erythemaPustules = diagnosisResult.getErythemaPustules();
    }
}
