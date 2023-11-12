package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class NotSMSSentException extends LocalizedMessageException {

    public NotSMSSentException() {
        super(HttpStatus.BAD_REQUEST, "required.sms-sending");
    }
}
