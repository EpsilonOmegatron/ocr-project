package com.personal.ocr_project.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.ocr_project.dto.UserDto;
import com.personal.ocr_project.mapper.UserMapper;
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
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

}
