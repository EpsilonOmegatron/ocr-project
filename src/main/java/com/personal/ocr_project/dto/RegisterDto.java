package com.personal.ocr_project.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String username;
    private String password;
    private Set<String> roles;
}
