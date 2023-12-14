package com.example.techit7.global.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {

    private final String DIR_PATH = System.getenv("userprofile") + "/images/";
    public String getFullPath(String filename) {

        return DIR_PATH + filename;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String originalFilename= multipartFile.getOriginalFilename();
        String storeFilename = getStoreFileName(originalFilename);

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