package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileUploadDownloadApplication {
    public static void main(String[] args) {
        /* to monitor or profile this service, first start jconsole /visualvm. then start this service.
        for java profiler use, start REST API using below command:
        java -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9990
        -Dcom.sun.management.jmxremote.authenticate=false -Xms768m -Xmx1600m -XX:+UseG1GC
        -XX:MaxGCPauseMillis=8000 -Xlog:gc*:file=./logs/gc.log:time,uptime,pid,tid:filecount=5,filesize=10m
        -jar target\file-upload-download-service-1.0-SNAPSHOT.jar
        */
        SpringApplication.run(FileUploadDownloadApplication.class, args);
    }
}