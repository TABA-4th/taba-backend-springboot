package com.taba.nimonaemo.diagnosis.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
public class ResponseAverageByAgeDTO {
    private final String gender;
    private final String old;
    private final double avgFindDeadSkinCells;
    private final double avgExcessSebum;
    private final double avgErythemaBetweenHairFollicles;
    private final double avgDandruff;
    private final double avgHairLoss;
    private final double avgErythemaPustules;

    @Builder
    private ResponseAverageByAgeDTO(@NotNull String gender,
                                    @NotNull String old,
                                    @NotNull double avgFindDeadSkinCells,
                                    @NotNull double avgExcessSebum,
                                    @NotNull double avgErythemaBetweenHairFollicles,
                                    @NotNull double avgDandruff,
                                    @NotNull double avgHairLoss,
                                    @NotNull double avgErythemaPustules) {
        this.gender = gender;
        this.old = old;
        this.avgFindDeadSkinCells = avgFindDeadSkinCells;
        this.avgExcessSebum = avgExcessSebum;
        this.avgErythemaBetweenHairFollicles = avgErythemaBetweenHairFollicles;
        this.avgDandruff = avgDandruff;
        this.avgHairLoss = avgHairLoss;
        this.avgErythemaPustules = avgErythemaPustules;
    }
}
