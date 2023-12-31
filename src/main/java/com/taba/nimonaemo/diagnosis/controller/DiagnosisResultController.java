package com.taba.nimonaemo.diagnosis.controller;

import com.taba.nimonaemo.diagnosis.exception.DiagnosisResultNotFoundException;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDetailDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisCountDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.service.DiagnosisResultService;
import com.taba.nimonaemo.global.auth.jwt.AppAuthentication;
import com.taba.nimonaemo.global.auth.role.MemberAuth;
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
     * @param date (ex. 2023/11)
     * @return 특정 달의 멤버에 대한 두피 진단 결과
     */
    @PostMapping("/result")
    @MemberAuth
    public List<ResponseDiagnosisResultDTO> result(AppAuthentication auth,
                                                   @Valid @RequestParam(name = "DATE") String date) throws FileNotFoundException, IOException, InvalidFormatException {
        return diagnosisResultService.findDiagnosisResult(auth.getUserId(), date);
    }

    /**
     * 멤버 별 특정 두피 진단 결과 삭제
     *
     * @param date 삭제하고자 하는 정확한 날짜 (ex. 2023-11-23 07:14:05)
     * <p>현재 캘린더에서 두피 진단 결과 기록의 제목이 측정한 날짜이므로</p>
     * <p>제목(날짜)을 request param에 담아서 보내주면 됩니다.</p>
     */
    @DeleteMapping("/result")
    @MemberAuth
    public void deleteResult(AppAuthentication auth,
                             @Valid @RequestParam(name = "DATE") String date) {
        try {
            diagnosisResultService.deleteDiagnosisResult(auth.getUserId(), date);
        } catch(Exception e) {
            throw new DiagnosisResultNotFoundException();
        }
    }

    /**
     * 멤버 별 두피 진단 총 검사 건수
     *
     * @return 멤버 별 두피 진단 총 검사 건수
     */
    @PostMapping("/count")
    @MemberAuth
    public ResponseDiagnosisCountDTO count(AppAuthentication auth) {
        return diagnosisResultService.findDiagnosisCount(auth.getUserId());
    }

    /**
     * 멤버 별 두피 진단 결과 특정 날짜 상세 조회
     * @param date 찾고자하는 정확한 날짜 (ex. 2023-11-23 07:14:05)
     *             
     * @return 특정 날짜에 대한 두피 진단 결과
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidFormatException
     */
    @PostMapping("/result/detail")
    @MemberAuth
    public ResponseDetailDiagnosisResultDTO DetailResult(AppAuthentication auth,
                                                         @Valid @RequestParam(name = "DATE") String date) throws FileNotFoundException, IOException, InvalidFormatException{
        return diagnosisResultService.findDetailDiagnosisResult(auth.getUserId(), date);
    }
}
