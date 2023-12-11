package com.taba.nimonaemo.member.controller;

import com.taba.nimonaemo.global.auth.role.MemberAuth;
import com.taba.nimonaemo.member.model.dto.request.RequestLoginDto;
import com.taba.nimonaemo.member.model.dto.request.RequestSignupDto;
import com.taba.nimonaemo.member.model.dto.response.ResponseLoginDto;
import com.taba.nimonaemo.member.model.dto.response.ResponseRefreshTokenDto;
import com.taba.nimonaemo.member.model.dto.response.ResponseSignupTokenDto;
import com.taba.nimonaemo.member.service.MemberService;
import com.taba.nimonaemo.member.service.SignupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name = "사용자", description = "사용자 관련 api")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final MemberService memberService;
    private final SignupService signupService;
//    private final MemberWithdrawService memberWithdrawService;

    /**
     * 회원가입 토큰 생성
     *
     * @return 회원가입 토큰
     */
    @GetMapping("/signup-token")
    public ResponseSignupTokenDto generateSignupToken() {
        return signupService.generateSignupToken();
    }

    /**
     * 회원가입
     *
     * @param dto           요청 body
     * @param signupToken   회원가입 토큰
     */
    @PostMapping("/{signup-token}")
    public void signup(@Valid @RequestBody RequestSignupDto dto,
                       @PathVariable("signup-token") String signupToken) {
        signupService.signup(dto, signupToken);
    }

    /**
     * 로그인
     *
     * @param dto           요청 body
     * @return              로그인 인증 정보
     */
    @PostMapping("/login")
    public ResponseLoginDto login(@Valid @RequestBody RequestLoginDto dto) {
        return memberService.login(dto);
    }

    /**
     * 토큰 재발급
     *
     * @param refreshToken  멤버의 refreshToken
     * @return 재발급된 로그인 인증 정보
     */
    @PostMapping("/reissue")
    @MemberAuth
    public ResponseRefreshTokenDto refreshToken(HttpServletRequest request, 
                                                @Valid @RequestParam String refreshToken) {
        return memberService.refreshToken(request, refreshToken);
    }

//    /**
//     * 회원탈퇴
//     * <p>회원은 바로 삭제되지 않고, 일정 기간 뒤에 삭제됩니다. 삭제시에도 개인 정보만 삭제됩니다.</p>
//     */
//    @DeleteMapping
//    public void withdraw(AppAuthentication auth) {
//        memberWithdrawService.withdraw(auth.getUserId());
//    }
}
