package com.personal.ocr_project.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OCRException.class)
    public ResponseEntity<ErrorResponse> handleOCRException(OCRException exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage(),
                webRequest.getDescription(false), "OCR_ERROR");

        if (errorResponse.getMessage().contains("format")) {
            errorResponse.setMessage("Invalid file format");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ErrorResponse> handleFileException(FileException exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage(),
                webRequest.getDescription(false), "FILE_OPERATION_ERROR");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage(),
                webRequest.getDescription(false), "USER_OPERATION_ERROR");

        if (errorResponse.getMessage().contains("constraint")) {
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        } else if (errorResponse.getMessage().contains("password")) {
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        } else if (errorResponse.getMessage().contains("null")) {
            errorResponse.setMessage("Missing or erroneous field.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
