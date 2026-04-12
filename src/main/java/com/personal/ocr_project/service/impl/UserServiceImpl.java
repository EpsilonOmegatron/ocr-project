package com.personal.ocr_project.service.impl;

import org.springframework.stereotype.Service;

import com.personal.ocr_project.dto.UserDto;
import com.personal.ocr_project.entity.User;
import com.personal.ocr_project.exception.BadCredentialsException;
import com.personal.ocr_project.exception.ResourceNotFoundException;
import com.personal.ocr_project.exception.UserException;
import com.personal.ocr_project.repository.UserRepository;
import com.personal.ocr_project.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public String login(UserDto usersDto) {
        User user = userRepository.findByUsername(usersDto.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist."));

        if (!user.getPassword().equals(usersDto.getPassword())) {
            throw new BadCredentialsException("Incorrect password.");
        }

        return "User logged-in successfully!";
    }

    @Override
    public String register(UserDto userDto) {
        try {
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setName(userDto.getName());
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }

        return "User registered successfully!";
    }

}
