package com.taba.nimonaemo.record.service;

import com.taba.nimonaemo.global.generator.NextMonthGenerator;
import com.taba.nimonaemo.member.exception.UserNotFoundException;
import com.taba.nimonaemo.member.model.entity.Member;
import com.taba.nimonaemo.member.repository.MemberRepository;
import com.taba.nimonaemo.record.exception.DiaryNotFoundException;
import com.taba.nimonaemo.record.model.dto.response.ResponseDiaryDTO;
import com.taba.nimonaemo.record.model.entity.Diary;
import com.taba.nimonaemo.record.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {

    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;

    @Transactional
    public void setMemberDiary(Long memberId,
                               LocalDate date,
                               String content) {
        Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);

        Diary diary = Diary.builder()
                .member(member)
                .diaryDate(date)
                .content(content)
                .build();
        diaryRepository.save(diary);
    }

    public List<ResponseDiaryDTO> getMemberDiary(Long memberId,
                                                 String date) {
        String nextMonth = NextMonthGenerator.generateNextMonth(date);
        List<Diary> diaryList = diaryRepository.findByDate(memberId, date, nextMonth);
        List<ResponseDiaryDTO> result = new ArrayList<>();

        if (!diaryList.isEmpty()) {
            for (int i = 0; i < diaryList.size(); i++) {
                ResponseDiaryDTO responseDto = ResponseDiaryDTO.builder()
                        .diary(diaryList.get(i))
                        .build();
                result.add(responseDto);
            }
            return result;
        } else {
            throw new DiaryNotFoundException();
        }
    }

    @Transactional
    public void deleteDiary(Long memberId,
                            Long diaryId) {
        diaryRepository.deleteByDate(memberId, diaryId);
    }
}