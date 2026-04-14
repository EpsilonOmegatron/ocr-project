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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        // Validate the username and password sent to log in, set them as an
        // authentication object in security context
        log.info("Recieved credentials from request, validating..");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("User authenticated from database. Generating JWT token now..");
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {

        // User exists check
        log.info("Checking if username: user={} exists or not..", registerDto.getUsername());
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        // Map RegisterDto to User
        log.info("Registering user with username: user={} to the database..", registerDto.getUsername());
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

        // Finally register the user to the database
        userRepository.save(user);

        return "User registered successfully!";
    }

}
