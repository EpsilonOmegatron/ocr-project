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

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));

                Set<Role> roles = user.getRoles();
                Set<GrantedAuthority> authorities = roles.stream()
                                .map((role) -> new SimpleGrantedAuthority(role.toString()))
                                .collect(Collectors.toSet());

                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                                authorities);
        }

}
