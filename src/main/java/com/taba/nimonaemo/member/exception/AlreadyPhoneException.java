package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class AlreadyPhoneException extends LocalizedMessageException {

    public AlreadyPhoneException() {
        super(HttpStatus.BAD_REQUEST, "already.phone");
    }
}
