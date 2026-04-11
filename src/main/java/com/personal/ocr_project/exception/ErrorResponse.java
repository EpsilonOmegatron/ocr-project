package com.personal.ocr_project.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message, String details, String errorCode) {

}
