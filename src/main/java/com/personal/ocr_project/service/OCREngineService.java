package com.personal.ocr_project.service;

import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.enums.OCR;

public interface OCREngineService {
    String extractTextFromImage(MultipartFile file);

    OCR getType();
}
