package com.taba.nimonaemo.record.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class HairStatusFlagNotFoundException extends LocalizedMessageException {
    public HairStatusFlagNotFoundException() { super(HttpStatus.NOT_FOUND, "notfound.hair-status-flag"); }
}
