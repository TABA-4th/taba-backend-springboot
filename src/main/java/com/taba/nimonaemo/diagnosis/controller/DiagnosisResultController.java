package com.taba.nimonaemo.diagnosis.controller;

import com.taba.nimonaemo.diagnosis.model.dto.request.RequestMemberDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisCountDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.service.DiagnosisResultService;
import com.taba.nimonaemo.global.auth.jwt.AppAuthentication;
import com.taba.nimonaemo.global.auth.role.MemberAuth;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "두피 진단 결과 조회", description = "두피 진단 결과 조회 api")
@RestController
@RequestMapping("/diagnosis")
@RequiredArgsConstructor
public class DiagnosisResultController {
    private final DiagnosisResultService diagnosisResultService;

    /**
     * 멤버 별 두피 진단 결과 조회
     *
     * @param dto           멤버 정보와 찾고자하는 달 요청 DTO
     * @return              특정 달의 멤버에 대한 두피 진단 결과
     */
    @PostMapping("/result")
    public List<ResponseDiagnosisResultDTO> result(@Valid @RequestBody RequestMemberDTO dto) {
        return diagnosisResultService.findDiagnosisResult(dto);
    }

    /**
     * 멤버 별 두피 진단 총 검사 건수
     *
     * @param nickname      멤버 닉네임
     * @return              멤버 별 두피 진단 총 검사 건수
     */
    @PostMapping("/count")
    public ResponseDiagnosisCountDTO count(@Valid @RequestParam String nickname) {
        return diagnosisResultService.findDiagnosisCount(nickname);
    }
}
