package com.taba.nimonaemo.member.service;

import com.taba.nimonaemo.global.auth.jwt.AuthenticationToken;
import com.taba.nimonaemo.global.auth.jwt.JwtProvider;
import com.taba.nimonaemo.member.exception.UserNotFoundException;
import com.taba.nimonaemo.member.exception.WrongPasswordException;
import com.taba.nimonaemo.member.model.dto.AutoLoginDto;
import com.taba.nimonaemo.member.model.dto.request.RequestLoginDto;
import com.taba.nimonaemo.member.model.dto.response.ResponseLoginDto;
import com.taba.nimonaemo.member.model.dto.response.ResponseRefreshTokenDto;
import com.taba.nimonaemo.member.model.entity.Member;
import com.taba.nimonaemo.member.repository.AutoLoginRepository;
import com.taba.nimonaemo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    public static final String AUTO_LOGIN_NAME = "auto-login";

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AutoLoginRepository autoLoginRepository;

    public ResponseLoginDto login(RequestLoginDto dto) {
        Instant now = Instant.now();
        Member member = memberRepository.findByNickname(dto.getNickname())
                .orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            AuthenticationToken token = jwtProvider.issue(member);
            autoLoginRepository.setAutoLoginPayload(token.getRefreshToken(), AUTO_LOGIN_NAME,
                    new AutoLoginDto(member.getId().toString(), member.getMemberRole()), now);
            return new ResponseLoginDto(token, member);
        } else {
            throw new WrongPasswordException();
        }
    }

    public ResponseRefreshTokenDto refreshToken(HttpServletRequest request, String refreshToken) {
        String accessToken = jwtProvider.getAccessTokenFromHeader(request);
        AuthenticationToken token = jwtProvider.reissue(accessToken, refreshToken);
        return new ResponseRefreshTokenDto(token);
    }

}
