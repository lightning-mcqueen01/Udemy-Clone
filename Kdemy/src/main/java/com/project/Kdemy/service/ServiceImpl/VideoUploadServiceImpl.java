package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.service.VideoUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class VideoUploadServiceImpl implements VideoUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String uploadVideo(MultipartFile file) throws IOException {

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir, filename);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return "/videos/" + filename;
    }
}
