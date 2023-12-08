package com.taba.nimonaemo.diagnosis.service;

import com.taba.nimonaemo.diagnosis.exception.DiagnosisResultNotFoundException;
import com.taba.nimonaemo.diagnosis.exception.SurveyNotFoundException;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestDeleteDiagnosisInfoDTO;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestDetailMemberDTO;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestMemberDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseAverageByAgeDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDetailDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisCountDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import com.taba.nimonaemo.diagnosis.model.entity.Survey;
import com.taba.nimonaemo.diagnosis.repository.DiagnosisResultRepository;
import com.taba.nimonaemo.diagnosis.repository.SurveyRepository;
import com.taba.nimonaemo.global.generator.NextMonthGenerator;
import com.taba.nimonaemo.member.exception.UserNotFoundException;
import com.taba.nimonaemo.member.model.entity.Member;
import com.taba.nimonaemo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import static org.apache.poi.ss.util.SheetUtil.getCell;

@Service
@Component
@RequiredArgsConstructor
public class DiagnosisResultService {

    Map<String, Integer> averageMap = new HashMap<>() {{
       put("0male", 0);
        put("0female", 1);
        put("10male", 2);
        put("10female", 3);
        put("20male", 4);
        put("20female", 5);
        put("30male", 6);
        put("30female", 7);
        put("40male", 8);
        put("40female", 9);
        put("50male", 10);
        put("50female", 11);
        put("60male", 12);
        put("60female", 13);
        put("70male", 14);
        put("70female", 15);
        put("80female", 16);
    }};

    @Value("${excel.path}")
    private String resourcePath;

    private final DiagnosisResultRepository diagnosisResultRepository;

    private final MemberRepository memberRepository;

    private final SurveyRepository surveyRepository;

    public List<ResponseDiagnosisResultDTO> findDiagnosisResult(RequestMemberDTO dto) {
        String nextMonth = NextMonthGenerator.generateNextMonth(dto.getDate());
        Member member = memberRepository.findByNickname(dto.getNickname()).orElseThrow(UserNotFoundException::new);

        List<DiagnosisResult> diagnosisResultList = diagnosisResultRepository.findByDate(member.getId(), dto.getDate(), nextMonth);
        List<ResponseDiagnosisResultDTO> result = new ArrayList<>();

        if (!diagnosisResultList.isEmpty()) {
            for (int i = 0; i < diagnosisResultList.size(); i++) {
                ResponseDiagnosisResultDTO responseDto = ResponseDiagnosisResultDTO.builder()
                        .diagnosisResult(diagnosisResultList.get(i))
                        .build();
                result.add(responseDto);
            }
            return result;
        } else {
            throw new DiagnosisResultNotFoundException();
        }
    }

    public ResponseDetailDiagnosisResultDTO findDetailDiagnosisResult(RequestDetailMemberDTO dto) throws FileNotFoundException, IOException, InvalidFormatException {
        Member member = memberRepository.findByNickname(dto.getNickname()).orElseThrow(UserNotFoundException::new);

        DiagnosisResult diagnosisResult = diagnosisResultRepository.findByDateForDetail(member.getId(), changeLocalDateTimeFormat(dto.getDate())).orElseThrow(DiagnosisResultNotFoundException::new);
        Survey survey = surveyRepository.findByDate(member.getId(), changeLocalDateTimeFormat(dto.getDate())).orElseThrow(SurveyNotFoundException::new);

        ResponseDetailDiagnosisResultDTO responseDto = ResponseDetailDiagnosisResultDTO.builder()
                .diagnosisResult(diagnosisResult)
                .responseAverageByAgeDTO(getAverageByAgeInExcel(survey.getGender(), survey.getOld()))
                .build();

        return responseDto;
    }

    public void deleteDiagnosisResult(RequestDeleteDiagnosisInfoDTO dto) {
        Member member = memberRepository.findByNickname(dto.getNickname()).orElseThrow(UserNotFoundException::new);
        diagnosisResultRepository.deleteByDate(member.getId(), changeLocalDateTimeFormat(dto.getDate()));
    }

    public ResponseDiagnosisCountDTO findDiagnosisCount(String nickname) {
        ResponseDiagnosisCountDTO responseDto = ResponseDiagnosisCountDTO.builder()
                .total(diagnosisResultRepository.findAllWithNickname(nickname))
                .build();
        return responseDto;
    }

    private LocalDateTime changeLocalDateTimeFormat(String targetDate) {
        LocalDateTime formatLocalDateTimeNow = LocalDateTime.parse(targetDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("String to LocalDatetime : " + formatLocalDateTimeNow);
        return formatLocalDateTimeNow;
    }

    private ResponseAverageByAgeDTO getAverageByAgeInExcel(String gender, String old) throws FileNotFoundException, IOException, InvalidFormatException {
        String target = old + gender;

        File file = new File(resourcePath);

        InputStream inputStream = new FileInputStream(file);
        OPCPackage opcPackage = OPCPackage.open(inputStream);
        Workbook workbook = null;
        try {
            if (resourcePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(opcPackage);
            }
            else {
                throw new RuntimeException("File type not matched");
            }

            if (workbook == null) {
                throw new RuntimeException("No data in file");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheetAt(0);

        Integer averageIndex = averageMap.get(target);

        ResponseAverageByAgeDTO dto = ResponseAverageByAgeDTO.builder()
                .gender(gender)
                .old(old)
                .avgFindDeadSkinCells(getCell(sheet, 1, averageIndex).getNumericCellValue())
                .avgExcessSebum(getCell(sheet, 2, averageIndex).getNumericCellValue())
                .avgErythemaBetweenHairFollicles(getCell(sheet, 3, averageIndex).getNumericCellValue())
                .avgErythemaPustules(getCell(sheet, 4, averageIndex).getNumericCellValue())
                .avgDandruff(getCell(sheet, 5, averageIndex).getNumericCellValue())
                .avgHairLoss(getCell(sheet, 6, averageIndex).getNumericCellValue())
                .build();

        return dto;
    }
}
