package com.personal.ocr_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.ocr_project.entity.ScanHistory;

public interface ScanHistoryRepository extends JpaRepository<ScanHistory, Long> {

}
