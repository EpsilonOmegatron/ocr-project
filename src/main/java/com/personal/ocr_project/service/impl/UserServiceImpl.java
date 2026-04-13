package com.personal.ocr_project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.ocr_project.entity.User;
import com.personal.ocr_project.repository.UserRepository;
import com.personal.ocr_project.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public String userCheck() {
        return "User can only call this.";
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
