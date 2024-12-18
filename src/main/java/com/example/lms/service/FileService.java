package com.example.lms.service;

import com.example.lms.model.course_related.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileEntity saveFile(MultipartFile file) throws IOException;

    FileEntity getFile(int id);
}
