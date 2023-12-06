package com.taba.nimonaemo.record.model.dto.response;

import com.taba.nimonaemo.record.model.entity.HairStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ResponseHairStatusDTO {
    private final LocalDate startPermDate;
    private final LocalDate startDyeDate;

    @Builder
    private ResponseHairStatusDTO(HairStatus hairStatus) {
        this.startPermDate = hairStatus.getStartPermDate();
        this.startDyeDate = hairStatus.getStartDyeDate();
    }
}
