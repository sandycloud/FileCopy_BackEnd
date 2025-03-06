package org.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/apiv1/files")
public class FileController {
    private static final Logger logger = LogManager.getLogger(FileController.class);

    //avoid using autowired here since it affects immutability.
    private final FileService fileService;
    
    //@Autowired
    public FileController(@Autowired FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            logger.debug("Uploading file:" +file.getName());
            String fileName = fileService.uploadFile(file);
            file.getInputStream().close();
            file = null;
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException ex) {
            logger.error("IO exception occurred: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Could not upload the file: " + file.getOriginalFilename());
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Resource resource = fileService.downloadFile(fileName);
            if (resource != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException ex) {
            logger.error("Error occurred while downloading file: {}", fileName, ex);
            return ResponseEntity.badRequest().body(null);
        }
    }
}