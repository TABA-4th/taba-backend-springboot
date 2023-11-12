package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class WrongSMSCodeException extends LocalizedMessageException {

    public WrongSMSCodeException(){
        super(HttpStatus.FORBIDDEN, "required.sms-authorization");
    }
}
