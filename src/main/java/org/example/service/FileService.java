package org.example.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileService {
    String uploadFile(MultipartFile file) throws IOException;
    Resource downloadFile(String fileName) throws MalformedURLException;
}