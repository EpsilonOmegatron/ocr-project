package com.personal.ocr_project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.personal.ocr_project.entity.User;
import com.personal.ocr_project.service.UserService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("check")
    public ResponseEntity<String> userCheck() {
        return ResponseEntity.ok(userService.userCheck());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
