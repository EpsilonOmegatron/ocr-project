package com.personal.ocr_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@Configuration
public class TesseractConfig {

    // Get tessdata file DIRECTORY from application.properties
    @Value("${tesseract.datapath}")
    private String dataPath;

    // Same for the language option
    @Value("${tesseract.language}")
    private String language;

    @Bean
    ITesseract tesseract() {
        Tesseract t = new Tesseract();

        t.setDatapath(dataPath);
        t.setLanguage(language);
        t.setOcrEngineMode(1);
        t.setPageSegMode(6);

        return t;
    }

}
