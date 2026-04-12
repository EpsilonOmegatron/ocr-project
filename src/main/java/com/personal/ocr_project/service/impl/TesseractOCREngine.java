package com.personal.ocr_project.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.exception.OCRException;
import com.personal.ocr_project.service.FileHandlerService;
import com.personal.ocr_project.service.OCREngineService;

import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
@AllArgsConstructor
@Primary
public class TesseractOCREngine implements OCREngineService {

    private ITesseract tesseract;
    private FileHandlerService fileHandlerService;

    @Override
    public String extractTextFromImage(MultipartFile file) {
        try {
            return tesseract.doOCR(fileHandlerService.handleMultipartFile(file));
        } catch (TesseractException e) {
            throw new OCRException(e.getMessage());
        }
    }

    @Override
    public OCR getType() {
        return OCR.TESSERACT;
    }

}
