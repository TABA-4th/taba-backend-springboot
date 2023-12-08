package com.taba.nimonaemo.diagnosis.exception;

import com.taba.nimonaemo.global.error.exception.LocalizedMessageException;
import org.springframework.http.HttpStatus;

public class SurveyNotFoundException extends LocalizedMessageException {
    public SurveyNotFoundException() { super(HttpStatus.NOT_FOUND, "notfound.survey"); }
}
