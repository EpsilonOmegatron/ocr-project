package com.personal.ocr_project.exception;

import java.time.LocalDateTime;

import com.personal.ocr_project.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private ErrorCode errorCode;

}
