package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);
    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public FileServiceImpl() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            logger.error("Could not create the directory where the uploaded files will be stored.", ex);
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /*@Override
    public String uploadFile(MultipartFile file) throws IOException {
        logger.debug("Uploading file");
        Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetLocation);
        logger.info("File uploaded successfully: {}", file.getOriginalFilename());
        return file.getOriginalFilename();
    }*/

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        logger.debug("Uploading file");
        Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());

        //after uploadig 5 files(avg 250MB) at steady state it consumes 1248 MB of memory.
        try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            //inputStream.close();
        }
        logger.info("File uploaded successfully: {}", file.getOriginalFilename());
        targetLocation =null;

        return file.getOriginalFilename();
    }

    @Override
    public Resource downloadFile(String fileName) throws MalformedURLException {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            logger.info("File downloaded successfully: {}", fileName);
            return resource;
        } else {
            logger.warn("File not found: {}", fileName);
            return null;
        }
    }
}