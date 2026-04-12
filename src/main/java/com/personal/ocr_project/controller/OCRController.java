package com.personal.ocr_project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.service.impl.OCREngineServiceImpl;

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

    private OCREngineServiceImpl ocrEngineService;

    @GetMapping("yo")
    public String test() {
        return "Yo!";
    }

    @PostMapping("ocr")
    public ResponseEntity<String> extractText(@RequestParam MultipartFile file, @RequestParam OCR engine) {
        return ResponseEntity.ok(ocrEngineService.extractTextFromImage(file, engine));
    }

}
