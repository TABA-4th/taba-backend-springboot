package com.taba.nimonaemo.member.service;

import com.taba.nimonaemo.member.exception.UserNotFoundException;
import com.taba.nimonaemo.member.model.MemberStatus;
import com.taba.nimonaemo.member.model.entity.Member;
import com.taba.nimonaemo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberWithdrawService {
    private final MemberRepository memberRepository;

    @Transactional
    public void withdraw(Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        member.changeStatus(MemberStatus.INACTIVE);
    }
}
