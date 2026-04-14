package com.personal.ocr_project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.service.impl.OCREngineServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RestController
@RequestMapping("api")
@AllArgsConstructor
public class OCRController {

    private OCREngineServiceImpl ocrEngineService;

    @GetMapping("yo")
    public String test() {
        return "Yo!";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("ocr")
    public ResponseEntity<String> extractText(@RequestParam MultipartFile file, @RequestParam OCR engine,
            @AuthenticationPrincipal UserDetails userDetails) {
        // Authentication Principal allows me to access the user details stored
        // in the current security context. Pretty handy
        return ResponseEntity.ok(ocrEngineService.extractTextFromImageAndSave(file, engine, userDetails.getUsername()));
    }

}
