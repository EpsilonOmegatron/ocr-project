package com.personal.ocr_project.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.personal.ocr_project.enums.ErrorCode;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Error response builder to reduce code repetition
    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, ErrorCode code,
            WebRequest request) {

        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), message, request.getDescription(false), code);

        return new ResponseEntity<>(error, status);
    }

    // Handler for generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), ErrorCode.GENERIC_EXCEPTION,
                webRequest);
    }

    // Handler for OCR exceptions
    @ExceptionHandler(OCRException.class)
    public ResponseEntity<ErrorResponse> handleOCRException(OCRException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), ErrorCode.OCR_ERROR, webRequest);
    }

    // Handler for file exceptions
    @ExceptionHandler(FileException.class)
    public ResponseEntity<ErrorResponse> handleFileException(FileException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), ErrorCode.FILE_OPERATION_ERROR,
                webRequest);
    }

    // Handler for generic user exceptions
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleGenericUserException(UserException exception, WebRequest webRequest) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), ErrorCode.USER_OPERATION_ERROR,
                webRequest);
    }

    // Handler for bad credentials
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception,
            WebRequest webRequest) {
        return buildResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), ErrorCode.BAD_CREDENTIALS,
                webRequest);
    }

    // Handler for resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
            WebRequest webRequest) {
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage(), ErrorCode.RESOURCE_NOT_FOUND,
                webRequest);
    }
}
