package com.personal.ocr_project.service;

import java.util.List;

import com.personal.ocr_project.dto.UserDto;

public interface UserService {
    String userCheck();

    List<UserDto> getUsers();
}
