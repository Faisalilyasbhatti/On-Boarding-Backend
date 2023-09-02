package com.evantagesoft.service.file;

public class FileStorageProperties {
    private static String uploadDir;

    public static String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}