package com.personal.ocr_project.mapper;

import com.personal.ocr_project.dto.ScanHistoryDto;
import com.personal.ocr_project.entity.ScanHistory;

public class ScanHistoryMapper {

    public static ScanHistoryDto toDto(ScanHistory scan) {
        ScanHistoryDto dto = new ScanHistoryDto();
        dto.setId(scan.getId());
        dto.setScannedText(scan.getScannedText());
        return dto;
    }
}
