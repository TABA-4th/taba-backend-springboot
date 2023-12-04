package com.taba.nimonaemo.diagnosis.service;

import com.taba.nimonaemo.diagnosis.exception.DiagnosisResultNotFoundException;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestDeleteDiagnosisInfoDTO;
import com.taba.nimonaemo.diagnosis.model.dto.request.RequestMemberDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisCountDTO;
import com.taba.nimonaemo.diagnosis.model.dto.response.ResponseDiagnosisResultDTO;
import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import com.taba.nimonaemo.diagnosis.repository.DiagnosisResultRepository;
import com.taba.nimonaemo.global.generator.NextMonthGenerator;
import com.taba.nimonaemo.member.exception.UserNotFoundException;
import com.taba.nimonaemo.member.model.entity.Member;
import com.taba.nimonaemo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class DiagnosisResultService {
    private final DiagnosisResultRepository diagnosisResultRepository;

    private final MemberRepository memberRepository;

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
}
