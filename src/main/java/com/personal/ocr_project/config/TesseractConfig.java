package com.personal.ocr_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@Configuration
public class TesseractConfig {

    @Value("${tesseract.datapath}")
    private String dataPath;

    @Value("${tesseract.language}")
    private String language;

    @Bean
    ITesseract tesseract() {
        Tesseract t = new Tesseract();
        t.setDatapath(dataPath);
        t.setLanguage(language);
        return t;
    }

}
