package com.personal.ocr_project.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.exception.FileException;
import com.personal.ocr_project.service.FileHandlerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileHandlerServiceImpl implements FileHandlerService {

    // Get download directory path from applications.properties
    @Value("${downloads.directory}")
    private String downloadsDirectory;

    // Allowed file formats for OCR
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/png",
            "image/jpeg",
            "image/jpg",
            "image/bmp",
            "image/tiff");

    // Max file size in bytes
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    @Override
    public File handleMultipartFile(MultipartFile uploadedFile) {

        log.info("Validating recieved file..");
        validateFile(uploadedFile);

        try {
            log.info("Checking download directory..");
            Path dirPath = Path.of(downloadsDirectory);

            // Ensure directory exists
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Generate unique filename
            log.info("Generating unique filename..");
            String fileName = UUID.randomUUID() + "_" + uploadedFile.getOriginalFilename();
            Path filePath = dirPath.resolve(fileName);

            // Save file safely
            log.info("Saving the actual file, and sending returning its path..");
            Files.copy(uploadedFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toFile();

        } catch (IOException e) {
            throw new FileException("Failed to store file.");
        }
    }

    private void validateFile(MultipartFile file) {

        log.info("Checking if file is empty..");
        if (file == null || file.isEmpty()) {
            throw new FileException("File is empty");
        }

        log.info("Checking file size..");
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileException("File exceeds maximum size of 5MB");
        }

        String contentType = file.getContentType();

        log.info("Checking file type..");
        if (contentType == null || !ALLOWED_TYPES.contains(contentType.toLowerCase())) {
            throw new FileException("Unsupported file type");
        }
    }

}
