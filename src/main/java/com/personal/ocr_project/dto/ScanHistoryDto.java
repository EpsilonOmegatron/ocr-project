package com.personal.ocr_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// Same thing here. Infinite recursion because of the bidirectional relation
// between User and ScanHistory. ScanHistory would recursively call its
// User and the resulting user would call its ScanHistory. I thought it was
// pretty funny but my computer didn't share my sense of humor.
public class ScanHistoryDto {
    private Long id;
    private String scannedText;
}
