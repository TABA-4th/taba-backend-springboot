package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class WrongPasswordException extends LocalizedMessageException {

    public WrongPasswordException(){
        super(HttpStatus.BAD_REQUEST, "invalid.password");
    }
}
