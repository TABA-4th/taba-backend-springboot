package com.taba.nimonaemo.diagnosis.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class DiagnosisResultNotFoundException extends LocalizedMessageException {
    public DiagnosisResultNotFoundException() { super(HttpStatus.NOT_FOUND, "notfound.diagnosis-result"); }
}
