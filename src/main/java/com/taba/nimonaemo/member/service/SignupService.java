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
    //TODO NicknameFilter 필요

    @Transactional
    public void signup(RequestSignupDto dto, String signupToken) {
        checkAlreadyNickname(dto.getNickname());
        checkAlreadyName(dto.getName());

        String phone = smsVerificationService.getPhoneNumber(signupToken);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

//        Optional<User> inactiveUser = userRepository.findByInactiveByNickname(dto.getNickname());
        //TODO 회원탈퇴 로직이 필요할 시 구현

        Member user = Member.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .password(encryptedPassword)
                .phone(phone)
                .memberRole(MemberRole.MEMBER)
                .status(MemberStatus.ACTIVE)
                .build();
        memberRepository.save(user);

        deleteSignupAuths(signupToken);
    }

    public ResponseSignupTokenDto generateSignupToken() {
        String signupToken = SignupTokenGenerator.generateUUIDCode();
        return new ResponseSignupTokenDto(signupToken);
    }

    private void checkAlreadyName(String name) {
        Optional<Member> alreadyUser = memberRepository.findByName(name);
        if (alreadyUser.isPresent()){
            throw new AlreadyNameException();
        }
    }

    private void checkAlreadyNickname(String nickname) {
        Optional<Member> alreadyUser = memberRepository.findByNickname(nickname);
        if (alreadyUser.isPresent()) {
            throw new AlreadyNicknameException();
        }
    }

    private void deleteSignupAuths(String signupToken) {
        if (!smsVerificationService.deleteSMSAuth(signupToken)) {
            log.error("Can't delete user signup authentication: sms auth");
        }
    }
}