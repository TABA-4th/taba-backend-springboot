package com.taba.nimonaemo.diagnosis.service;

import com.taba.nimonaemo.diagnosis.exception.DiagnosisResultNotFoundException;
import com.taba.nimonaemo.diagnosis.exception.SurveyNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;

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

    public List<ResponseDiagnosisResultDTO> findDiagnosisResult(Long memberId, String date) {
        String nextMonth = NextMonthGenerator.generateNextMonth(date);
        Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);

        List<DiagnosisResult> diagnosisResultList = diagnosisResultRepository.findByDate(member.getId(), date, nextMonth);
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

    public ResponseDetailDiagnosisResultDTO findDetailDiagnosisResult(Long memberId, String date) throws FileNotFoundException, IOException, InvalidFormatException {

        DiagnosisResult diagnosisResult = diagnosisResultRepository.findByDateForDetail(memberId, changeLocalDateTimeFormat(date)).orElseThrow(DiagnosisResultNotFoundException::new);
        Survey survey = surveyRepository.findByDate(memberId, changeLocalDateTimeFormat(date)).orElseThrow(SurveyNotFoundException::new);

        ResponseDetailDiagnosisResultDTO responseDto = ResponseDetailDiagnosisResultDTO.builder()
                .diagnosisResult(diagnosisResult)
                .responseAverageByAgeDTO(getAverageByAgeInExcel(survey.getGender(), survey.getOld()))
                .build();

        return responseDto;
    }

    @Transactional
    public void deleteDiagnosisResult(Long memberId, String date) {
        diagnosisResultRepository.deleteByDate(memberId, changeLocalDateTimeFormat(date));
    }

    public ResponseDiagnosisCountDTO findDiagnosisCount(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);

        ResponseDiagnosisCountDTO responseDto = ResponseDiagnosisCountDTO.builder()
                .total(diagnosisResultRepository.findAllWithId(memberId))
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
