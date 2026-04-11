package com.personal.ocr_project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.service.OCRProviderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OCRService {
    private OCRProviderService ocrProvider;

    public String extractTextFromImage(MultipartFile file) {
        return ocrProvider.extractTextFromImage(file);
    }
}
