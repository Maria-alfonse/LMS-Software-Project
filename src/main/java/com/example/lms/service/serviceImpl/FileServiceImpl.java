package com.example.lms.service.serviceImpl;
import com.example.lms.model.course_related.FileEntity;
import com.example.lms.repository.FileRepo;
import com.example.lms.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final FileRepo fileRepository;

    // Save file to file system and save metadata to the database
    public FileEntity saveFile(MultipartFile file) throws IOException {
        // Generate file path
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadDir, fileName);

        // Ensure the directory exists
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Write the file to disk
        Files.write(path, file.getBytes());

        // Save file metadata to the database
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setPath(path.toString());
        fileEntity.setType(file.getContentType());

        return fileRepository.save(fileEntity);
    }

    // Retrieve file metadata
    public FileEntity getFile(int id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found!"));
    }
}