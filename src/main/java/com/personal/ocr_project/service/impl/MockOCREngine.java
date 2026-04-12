package com.personal.ocr_project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.service.OCREngineService;

@Service
public class MockOCREngine implements OCREngineService {

    @Override
    public String extractTextFromImage(MultipartFile file) {
        return "This is the mock OCR provider.";
    }

    @Override
    public OCR getType() {
        return OCR.MOCK;
    }

}
