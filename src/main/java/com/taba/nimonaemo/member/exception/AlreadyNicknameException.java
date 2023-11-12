package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class AlreadyNicknameException extends LocalizedMessageException {

    public AlreadyNicknameException() {
        super(HttpStatus.BAD_REQUEST, "already.nickname");
    }
}
