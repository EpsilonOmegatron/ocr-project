package com.personal.ocr_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScanHistoryDto {
    private Long id;
    private String scannedText;
}
