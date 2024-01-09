package com.example.techit7.global.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@ConfigurationProperties(prefix = "site.gen-file")
@RequiredArgsConstructor
public class FileStore {
    private final String DIR_PATH;

    public String getFullPath(String filename) {

        return DIR_PATH + filename;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String originalFilename= multipartFile.getOriginalFilename();
        String storeFilename = getStoreFileName(originalFilename);
        Path imagesDir = Paths.get(DIR_PATH);
        if (!Files.exists(imagesDir)) {
            Files.createDirectories(imagesDir);
        }
        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return storeFilename;
    }

    private String getStoreFileName(String originalFilename) {
        return UUID.randomUUID() + "." + extractExt(originalFilename);
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        if (pos == -1) {
            return "";
        }
        return originalFilename.substring(pos + 1);
    }

}