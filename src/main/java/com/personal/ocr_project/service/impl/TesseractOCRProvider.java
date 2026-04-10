package com.personal.ocr_project.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.service.OCRProvider;

import net.sourceforge.tess4j.ITesseract;

@Service
public class TesseractOCRProvider implements OCRProvider {

    private ITesseract tesseract;

    @Value("${downloads.directory}")
    private String downloadsDirectory;

    public TesseractOCRProvider(ITesseract tesseract) {
        this.tesseract = tesseract;
    }

    @Override
    public String extractTextFromImage(MultipartFile file) {
        try {
            File dir = new File(downloadsDirectory);
            File downloadedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(downloadedFile);
            return tesseract.doOCR(downloadedFile);
        } catch (Exception e) {
            throw new RuntimeException("Tesseract Error:", e);
        }
    }

}
