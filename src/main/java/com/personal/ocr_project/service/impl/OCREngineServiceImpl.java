package com.personal.ocr_project.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.entity.ScanHistory;
import com.personal.ocr_project.entity.User;
import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.exception.ResourceNotFoundException;
import com.personal.ocr_project.factory.OCREngineFactory;
import com.personal.ocr_project.repository.ScanHistoryRepository;
import com.personal.ocr_project.repository.UserRepository;

@Service
@AllArgsConstructor
public class OCREngineServiceImpl {

    private OCREngineFactory factory;
    private UserRepository userRepository;
    private ScanHistoryRepository scanHistoryRepository;

    // public String extractTextFromImage(MultipartFile file, OCR engine) {
    // return factory.getEngine(engine).extractTextFromImage(file);
    // }

    public String extractTextFromImageAndSave(MultipartFile file, OCR engine, String username) {
        // Fetch OCR engine from request, send to the factory to fetch the corresponding
        // extractTextFromImage function
        String text = factory.getEngine(engine).extractTextFromImage(file);

        // User existence check
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username doesn't exist."));

        // Set and save scan history
        ScanHistory scan = new ScanHistory();
        scan.setScannedText(text);
        scan.setUser(user);

        scanHistoryRepository.save(scan);

        return "OCR Scan successful! Saved to database.";
    }
}