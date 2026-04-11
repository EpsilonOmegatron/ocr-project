package com.personal.ocr_project.service;

import com.personal.ocr_project.dto.UserDto;

public interface UserService {
    String login(UserDto userDto);

    String register(UserDto userDto);
}
