package com.taba.nimonaemo.member.service;

import com.taba.nimonaemo.global.auth.role.MemberRole;
import com.taba.nimonaemo.global.generator.SignupTokenGenerator;
import com.taba.nimonaemo.member.exception.AlreadyNameException;
import com.taba.nimonaemo.member.exception.AlreadyNicknameException;
import com.taba.nimonaemo.member.model.MemberStatus;
import com.taba.nimonaemo.member.model.dto.request.RequestSignupDto;
import com.taba.nimonaemo.member.model.dto.response.ResponseSignupTokenDto;
import com.taba.nimonaemo.member.model.entity.Member;
import com.taba.nimonaemo.member.repository.MemberRepository;
import com.taba.nimonaemo.record.model.entity.HairStatus;
import com.taba.nimonaemo.record.repository.HairStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupService {

    private final SMSVerificationService smsVerificationService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final HairStatusRepository hairStatusRepository;

    //TODO NicknameFilter 필요

    @Transactional
    public void signup(RequestSignupDto dto, String signupToken) {
        checkAlreadyNickname(dto.getNickname());
        checkAlreadyName(dto.getName());

        String phone = smsVerificationService.getPhoneNumber(signupToken);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

//        Optional<User> inactiveUser = userRepository.findByInactiveByNickname(dto.getNickname());
        //TODO 회원탈퇴 로직이 필요할 시 구현

        Member member = Member.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .password(encryptedPassword)
                .phone(phone)
                .memberRole(MemberRole.MEMBER)
                .status(MemberStatus.ACTIVE)
                .build();
        memberRepository.save(member);

        HairStatus hairStatus = HairStatus.builder()
                .member(member)
                .build();
        hairStatusRepository.save(hairStatus);

        deleteSignupAuths(signupToken);
    }

    public ResponseSignupTokenDto generateSignupToken() {
        String signupToken = SignupTokenGenerator.generateUUIDCode();
        return new ResponseSignupTokenDto(signupToken);
    }

    private void checkAlreadyName(String name) {
        Optional<Member> alreadyMember = memberRepository.findByName(name);
        if (alreadyMember.isPresent()){
            throw new AlreadyNameException();
        }
    }

    public void checkAlreadyNickname(String nickname) {
        Optional<Member> alreadyMember = memberRepository.findByNickname(nickname);
        if (alreadyMember.isPresent()) {
            throw new AlreadyNicknameException();
        }
    }

    private void deleteSignupAuths(String signupToken) {
        if (!smsVerificationService.deleteSMSAuth(signupToken)) {
            log.error("Can't delete member signup authentication: sms auth");
        }
    }
}