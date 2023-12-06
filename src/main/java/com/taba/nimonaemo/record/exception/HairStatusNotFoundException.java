package com.taba.nimonaemo.record.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class HairStatusNotFoundException extends LocalizedMessageException {
    public HairStatusNotFoundException() { super(HttpStatus.NOT_FOUND, "notfound.hair-status"); }
}
