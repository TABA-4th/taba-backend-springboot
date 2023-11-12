package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends LocalizedMessageException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "notfound.user");
    }
}
