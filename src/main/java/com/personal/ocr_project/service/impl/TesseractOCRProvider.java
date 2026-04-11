package com.personal.ocr_project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.exception.OCRException;
import com.personal.ocr_project.service.FileHandlerService;
import com.personal.ocr_project.service.OCRProviderService;

import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.ITesseract;

@Service
@AllArgsConstructor
public class TesseractOCRProvider implements OCRProviderService {

    private ITesseract tesseract;
    private FileHandlerService fileHandlerService;

    @Override
    public String extractTextFromImage(MultipartFile file) {
        try {
            return tesseract.doOCR(fileHandlerService.handleMultipartFile(file));
        } catch (Exception e) {
            throw new OCRException(e.getMessage());
        }
    }

}
