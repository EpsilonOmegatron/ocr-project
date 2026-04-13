package com.personal.ocr_project.service.impl;

import com.personal.ocr_project.repository.RoleRepository;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.ocr_project.dto.LoginDto;
import com.personal.ocr_project.dto.RegisterDto;
import com.personal.ocr_project.entity.Role;
import com.personal.ocr_project.entity.User;
import com.personal.ocr_project.exception.ResourceNotFoundException;
import com.personal.ocr_project.exception.UsernameAlreadyExistsException;
import com.personal.ocr_project.repository.UserRepository;
import com.personal.ocr_project.security.JwtTokenProvider;
import com.personal.ocr_project.service.AuthService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setName(registerDto.getName());

        Set<Role> roles = new HashSet<>();
        for (String role : registerDto.getRoles()) {
            roles.add(roleRepository.findByName(role)
                    .orElseThrow(() -> new ResourceNotFoundException("Role doesn't exist.")));
        }
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!";
    }

}
