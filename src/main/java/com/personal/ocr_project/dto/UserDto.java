package com.personal.ocr_project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private List<ScanHistoryDto> scanHistory;
}
