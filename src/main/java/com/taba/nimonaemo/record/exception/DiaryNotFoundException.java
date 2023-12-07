package com.taba.nimonaemo.record.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class DiaryNotFoundException extends LocalizedMessageException {
    public DiaryNotFoundException() { super(HttpStatus.NOT_FOUND, "notfound.diary"); }
}
