package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileUploadDownloadApplication {
    public static void main(String[] args) {
        /* to monitor or profile this service, first start jconsole /visualvm. then start this service.
        for java profiler use, command to start REST API is in Readme
        */
        SpringApplication.run(FileUploadDownloadApplication.class, args);
    }
}