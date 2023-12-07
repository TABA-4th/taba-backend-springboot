package com.taba.nimonaemo.record.service;

import com.taba.nimonaemo.member.exception.UserNotFoundException;
import com.taba.nimonaemo.member.model.entity.Member;
import com.taba.nimonaemo.member.repository.MemberRepository;
import com.taba.nimonaemo.record.exception.HairStatusFlagNotFoundException;
import com.taba.nimonaemo.record.exception.HairStatusNotFoundException;
import com.taba.nimonaemo.record.model.dto.response.ResponseHairStatusDTO;
import com.taba.nimonaemo.record.model.entity.HairStatus;
import com.taba.nimonaemo.record.repository.HairStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class HairStatusService {
    private final MemberRepository memberRepository;
    private final HairStatusRepository hairStatusRepository;

    @Transactional
    public void setMemberHairStatus(Long memberId,
                                    LocalDate date,
                                    boolean permFlag,
                                    boolean dyeFlag) {
        HairStatus hairStatus = hairStatusRepository.findByMemberId(memberId).orElseThrow(HairStatusNotFoundException::new);

        if (permFlag && dyeFlag) {
            hairStatus.changeStartPermDate(date);
            hairStatus.changeStartDyeDate(date);
        }
        else if (permFlag) {
            hairStatus.changeStartPermDate(date);
        }
        else if (dyeFlag) {
            hairStatus.changeStartDyeDate(date);
        }
        else {
            throw new HairStatusFlagNotFoundException();
        }
    }

    public ResponseHairStatusDTO getMemberHairStatus(Long memberId) {
        HairStatus hairStatus = hairStatusRepository.findByMemberId(memberId).orElseThrow(HairStatusNotFoundException::new);

        ResponseHairStatusDTO responseDto = ResponseHairStatusDTO.builder()
                .hairStatus(hairStatus)
                .build();
        return responseDto;
    }

    @Transactional
    public void deleteMemberPermStatus(Long memberId) {
        HairStatus hairStatus = hairStatusRepository.findByMemberId(memberId).orElseThrow(HairStatusNotFoundException::new);

        hairStatus.changeStartPermDate(null);
    }

    @Transactional
    public void deleteMemberDyeStatus(Long memberId) {
        HairStatus hairStatus = hairStatusRepository.findByMemberId(memberId).orElseThrow(HairStatusNotFoundException::new);

        hairStatus.changeStartDyeDate(null);
    }
}
