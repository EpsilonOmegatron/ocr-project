package com.personal.ocr_project.service;

import java.util.List;

import com.personal.ocr_project.entity.User;

public interface UserService {
    String userCheck();

    List<User> getUsers();
}
