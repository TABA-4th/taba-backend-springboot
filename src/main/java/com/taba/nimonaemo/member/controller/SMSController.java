package com.taba.nimonaemo.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taba.nimonaemo.global.sms.service.SMSService;
import com.taba.nimonaemo.member.model.dto.request.RequestPhoneNumberDto;
import com.taba.nimonaemo.member.model.dto.request.RequestVerifySMSCodeDto;
import com.taba.nimonaemo.member.service.SMSVerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Tag(name = "SMS" , description = "SMS 관련 API")
@RequestMapping("sms")
public class SMSController {

    private final SMSVerificationService smsService;
    private final SMSService service;

    /**
     * 사용자 인증 SMS 전송
     */
    @PostMapping("/{signup-token}")
    public void sendSMS(@Valid @RequestBody RequestPhoneNumberDto dto,
                        @PathVariable("signup-token") String signupToken) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        smsService.sendSMSCode(signupToken, dto.getPhoneNumber());
    }

    @PostMapping("/verify/{signup-token}")
    public void verifySMSCode(@Valid @RequestBody RequestVerifySMSCodeDto dto,
                              @PathVariable("signup-token") String signupToken) {
        smsService.verifySMSCode(signupToken, dto.getCode());
    }
}
