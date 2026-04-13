package com.personal.ocr_project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponseDto {
    private final String accessToken;
    private String tokenType = "Bearer";
}
