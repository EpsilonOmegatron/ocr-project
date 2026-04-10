package com.personal.ocr_project.service;

import org.springframework.web.multipart.MultipartFile;

public interface OCRProvider {
    String extractTextFromImage(MultipartFile file);
}
