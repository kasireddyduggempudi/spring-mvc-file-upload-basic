package com.vedantu.requests;

import org.springframework.web.multipart.MultipartFile;

public class UploadRequest {
    private String username = null;
    private MultipartFile file= null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
