package com.taba.nimonaemo.diagnosis.controller;

import com.taba.nimonaemo.diagnosis.exception.DiagnosisResultNotFoundException;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestDeleteDiagnosisInfoDTO;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestDetailMemberDTO;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestMemberDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDetailDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisCountDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.service.DiagnosisResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public List<ResponseDiagnosisResultDTO> result(@Valid @RequestBody RequestMemberDTO dto) throws FileNotFoundException, IOException, InvalidFormatException {
        return diagnosisResultService.findDiagnosisResult(dto);
    }

    /**
     * TODO: MemberAuth와 토큰 적용해서 보안 문제 해결하기
     */
    /**
     * 멤버 별 특정 두피 진단 결과 삭제
     *
     * @param dto           멤버 정보와 삭제하고자 하는 정확한 날짜
     * <p>현재 캘린더에서 두피 진단 결과 기록의 제목이 측정한 날짜이므로</p>
     * <p>제목(날짜)을 request body에 담아서 보내주면 됩니다.</p>
     */
    @DeleteMapping("/result")
    public void deleteResult(@Valid @RequestBody RequestDeleteDiagnosisInfoDTO dto) {
        try {
            diagnosisResultService.deleteDiagnosisResult(dto);
        } catch(Exception e) {
            throw new DiagnosisResultNotFoundException();
        }
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

    /**
     * 멤버 별 두피 진단 결과 특정 날짜 상세 조회
     * @param dto           멤버 정보와 찾고자하는 정확한 날짜 요청 DTO
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidFormatException
     */
    @PostMapping("/result/detail")
    public ResponseDetailDiagnosisResultDTO DetailResult(@Valid @RequestBody RequestDetailMemberDTO dto) throws FileNotFoundException, IOException, InvalidFormatException{
        return diagnosisResultService.findDetailDiagnosisResult(dto);
    }
}
