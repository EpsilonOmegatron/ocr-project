package com.personal.ocr_project.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileHandlerService {
    File handleMultipartFile(MultipartFile uploadedFile);
}
