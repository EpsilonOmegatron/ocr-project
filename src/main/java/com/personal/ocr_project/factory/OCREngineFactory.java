package com.personal.ocr_project.factory;

import org.springframework.stereotype.Component;

import com.personal.ocr_project.enums.OCR;
import com.personal.ocr_project.service.OCREngineService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OCREngineFactory {

    // Define a map of OCR enum and link them to
    // different service implementations to apply startegy pattern
    private final Map<OCR, OCREngineService> engines;

    public OCREngineFactory(List<OCREngineService> services) {
        this.engines = services.stream()
                .collect(Collectors.toMap(
                        OCREngineService::getType,
                        s -> s));
    }

    // Pick and inject the service depending on
    // the decided engine type
    public OCREngineService getEngine(OCR type) {
        log.info("Generating engine service using engine type: engine={}", type);
        OCREngineService service = engines.get(type);

        if (service == null) {
            throw new IllegalArgumentException("Unsupported OCR engine: " + type);
        }

        return service;
    }
}