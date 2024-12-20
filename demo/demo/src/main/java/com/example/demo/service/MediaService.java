package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaService {

    @Value("${media.upload-dir}")
    private String uploadDir; // Get the upload directory from application.properties

    // Store the uploaded media file (video, PDF, etc.)
    public String storeMediaFile(MultipartFile file) throws IOException {
        // Create the directory if it doesn't exist
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // Get the original filename
        String originalFilename = file.getOriginalFilename();
        Path filePath = path.resolve(originalFilename);

        // Save the file to the directory
        file.transferTo(filePath.toFile());

        // Return the file's URL (or file path)
        return filePath.toString(); // You can return a relative URL instead if needed
    }
}
