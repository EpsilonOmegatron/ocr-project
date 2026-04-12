package com.personal.ocr_project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.factory.OCREngineFactory;

@Service
@AllArgsConstructor
public class OCREngineServiceImpl {

    private OCREngineFactory factory;

    public String extractTextFromImage(MultipartFile file, OCR engine) {
        return factory.getEngine(engine).extractTextFromImage(file);
    }
}