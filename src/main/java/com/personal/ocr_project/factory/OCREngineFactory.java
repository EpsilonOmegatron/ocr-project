package com.personal.ocr_project.factory;

import org.springframework.stereotype.Component;

import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.service.OCREngineService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OCREngineFactory {

    private final Map<OCR, OCREngineService> engines;

    public OCREngineFactory(List<OCREngineService> services) {
        this.engines = services.stream()
                .collect(Collectors.toMap(
                        OCREngineService::getType,
                        s -> s));
    }

    public OCREngineService getEngine(OCR type) {
        OCREngineService service = engines.get(type);

        if (service == null) {
            throw new IllegalArgumentException("Unsupported OCR engine: " + type);
        }

        return service;
    }
}