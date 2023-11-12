package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class AutoLoginUserNotFoundException extends LocalizedMessageException {

    public AutoLoginUserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "notfound.auto-login");
    }
}
