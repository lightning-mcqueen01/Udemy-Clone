package com.project.Kdemy.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoUploadService {

    public String uploadVideo(MultipartFile file) throws IOException;
}
