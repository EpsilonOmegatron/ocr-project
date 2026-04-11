package com.personal.ocr_project.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.personal.ocr_project.exception.FileException;
import com.personal.ocr_project.service.FileHandlerService;

@Service
public class FileHandlerServiceImpl implements FileHandlerService {

    @Value("${downloads.directory}")
    private String downloadsDirectory;

    @Override
    public File handleMultipartFile(MultipartFile uploadedFile) {
        try {
            File dir = new File(downloadsDirectory);
            File downloadedFile = new File(dir, uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(downloadedFile);
            return downloadedFile;
        } catch (Exception e) {
            throw new FileException("File error", e);
        }
    }

}
