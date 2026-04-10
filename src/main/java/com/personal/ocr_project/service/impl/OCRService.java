package com.personal.ocr_project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.service.OCRProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OCRService {
    private OCRProvider ocrProvider;

    public String extractTextFromImage(MultipartFile file) {
        return ocrProvider.extractTextFromImage(file);
    }
}
