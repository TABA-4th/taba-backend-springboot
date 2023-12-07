package com.taba.nimonaemo.record.model.dto.response;

import com.taba.nimonaemo.record.model.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ResponseDiaryDTO {
    private final Long diaryId;
    private final LocalDate diaryDate;
    private final String content;

    @Builder
    private ResponseDiaryDTO(Diary diary) {
        this.diaryId = diary.getId();
        this.diaryDate = diary.getDiaryDate();
        this.content = diary.getContent();
    }
}
