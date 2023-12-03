package com.taba.nimonaemo.diagnosis.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseDiagnosisCountDTO {
    private final Long total;

    @Builder
    private ResponseDiagnosisCountDTO(Long total) {
        this.total = total;
    }
}
