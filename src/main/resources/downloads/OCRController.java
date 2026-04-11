package com.personal.ocr_project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.service.impl.OCRService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class OCRController {

    private OCRService ocrService;

    @GetMapping("yo")
    public String test() {
        return "Yo!";
    }

    @PostMapping("ocr")
    public ResponseEntity<String> extractText(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(ocrService.extractTextFromImage(file));
    }

}
