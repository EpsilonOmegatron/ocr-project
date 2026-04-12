package com.personal.ocr_project.service;

import com.personal.ocr_project.dto.LoginDto;
import com.personal.ocr_project.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
