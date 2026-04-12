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

        validateFile(uploadedFile);

        try {
            Path dirPath = Path.of(downloadsDirectory);

            // Ensure directory exists
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Generate unique filename
            String fileName = UUID.randomUUID() + "_" + uploadedFile.getOriginalFilename();
            Path filePath = dirPath.resolve(fileName);

            // Save file safely
            Files.copy(uploadedFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toFile();

        } catch (IOException e) {
            throw new FileException("Failed to store file.");
        }
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new FileException("File is empty");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileException("File exceeds maximum size of 5MB");
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType.toLowerCase())) {
            throw new FileException("Unsupported file type");
        }
    }

}
