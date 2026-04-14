package com.personal.ocr_project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
// I realized the use of DTOs when I discovered that I returned the passwords
// by returning the full user. Even if they're hashed, it's a bit of a blunder
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private List<ScanHistoryDto> scanHistory;
}
