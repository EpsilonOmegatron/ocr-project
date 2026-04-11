package com.personal.ocr_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.ocr_project.dto.UserDto;
import com.personal.ocr_project.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @GetMapping("login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.login(userDto));
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        return new ResponseEntity<String>(userService.register(userDto), HttpStatus.CREATED);
    }

}
