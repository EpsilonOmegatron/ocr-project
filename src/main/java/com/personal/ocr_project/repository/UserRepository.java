package com.personal.ocr_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.ocr_project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
