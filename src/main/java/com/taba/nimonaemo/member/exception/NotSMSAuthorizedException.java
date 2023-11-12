package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class NotSMSAuthorizedException extends LocalizedMessageException {

    public NotSMSAuthorizedException() {
        super(HttpStatus.FORBIDDEN, "required.sms-authorization");
    }
}
