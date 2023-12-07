package com.taba.nimonaemo.record.controller;

import com.taba.nimonaemo.global.auth.jwt.AppAuthentication;
import com.taba.nimonaemo.global.auth.role.MemberAuth;
import com.taba.nimonaemo.record.model.dto.response.ResponseDiaryDTO;
import com.taba.nimonaemo.record.service.DiaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "다이어리", description = "사용자 별 다이어리 기능 관련 api")
@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    /**
     * 멤버 별 다이어리 기록
     *
     * @param date (ex. 2023-12-07)
     * @param content (ex. 두피 마사지샵 가는날)
     */
    @PostMapping
    @MemberAuth
    public void setMemberDiary(AppAuthentication auth,
                               @RequestParam(name = "DATE") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                               @RequestParam(name = "CONTENT") String content) {
        diaryService.setMemberDiary(auth.getUserId(), date, content);
    }

    /**
     * 멤버 별 특정 달의 다이어리 조회
     * @param date (ex. 2023/12)
     *
     * @return ResponseDiaryDTO
     */
    @GetMapping
    @MemberAuth
    public List<ResponseDiaryDTO> getMemberDiary(AppAuthentication auth,
                                                 @RequestParam(name = "DATE") String date) {
        return diaryService.getMemberDiary(auth.getUserId(), date);
    }

    /**
     * 멤버 별 특정 날짜 다이어리 내용 삭제
     *
     * @param diaryId (GET 요청에서 받은 diaryId value 값 ex. 24)
     */
    @DeleteMapping
    @MemberAuth
    public void deleteMemberDiary(AppAuthentication auth,
                                  @RequestParam(name = "DIARY_ID") Long diaryId) {
        diaryService.deleteDiary(auth.getUserId(), diaryId);
    }
}