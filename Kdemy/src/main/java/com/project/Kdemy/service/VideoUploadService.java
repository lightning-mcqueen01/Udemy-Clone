package com.project.Kdemy.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoUploadService {

    public String uploadVideo(MultipartFile file);
}
