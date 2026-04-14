package com.personal.ocr_project.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personal.ocr_project.entity.Role;
import com.personal.ocr_project.entity.User;
import com.personal.ocr_project.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                log.info("Fetching user from database with their username: username={}", username);
                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

                log.info("User found! Extracting their roles to generate authorities..");
                Set<Role> roles = user.getRoles();
                Set<GrantedAuthority> authorities = roles.stream()
                                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toSet());

                log.info("Sending back generated user details.");
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                authorities);
        }

}
