package com.taba.nimonaemo.member.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class AlreadyNameException extends LocalizedMessageException {

    public AlreadyNameException(){
        super(HttpStatus.BAD_REQUEST, "already.name");
    }
}
