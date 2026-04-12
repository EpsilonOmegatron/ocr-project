package com.personal.ocr_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.ocr_project.dto.LoginDto;
import com.personal.ocr_project.dto.RegisterDto;
import com.personal.ocr_project.service.AuthService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private AuthService authService;

    @GetMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<String>(authService.register(registerDto), HttpStatus.CREATED);
    }

}
