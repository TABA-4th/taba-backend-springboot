package com.taba.nimonaemo.global.error.service;

import com.taba.nimonaemo.global.error.ErrorResponseDto;
import com.taba.nimonaemo.global.error.model.ErrorLog;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ErrorLogService {

    private final Map<String, ErrorLog> errors = new HashMap<>();

    public void logError(String trackingId, Throwable t, ErrorResponseDto dto) {
        String errorLog = ExceptionUtils.getStackTrace(t);
        errors.put(trackingId, new ErrorLog(dto, errorLog));
    }

    public String findErrorLog(String trackingId) {
        ErrorLog log = errors.get(trackingId);
        if (log != null) {
            return log.toString();
        } else {
            return "No error log found for that tracking ID: " + trackingId;
        }
    }
}
