package com.webshop.backend.service;

import com.webshop.backend.fileserrvice.FileService;
import com.webshop.backend.model.TopNewsImage;
import com.webshop.backend.repository.TopNewsImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@Service
public class TopNewsImageService {
    @Value("${photo.top.news.directory}")
    private String topNewsImageDirectory;

    @Autowired
    private TopNewsImageRepository topNewsImageRepository;

    @Autowired
    private FileService fileService;

    private static final boolean IS_ACTIVE_DEFAULT = true;
    private static final boolean IS_DELETED_DEFAULT = false;

    public TopNewsImage saveTopNewsImage(MultipartFile imageFile) throws IOException {

        String savedImagePath = fileService.saveImageIntoDirectory(imageFile, topNewsImageDirectory);
        TopNewsImage topNewsImage = new TopNewsImage();
        topNewsImage.setFilePath(savedImagePath);
        topNewsImage.setActive(IS_ACTIVE_DEFAULT);
        topNewsImage.setDeleted(IS_DELETED_DEFAULT);
        return topNewsImageRepository.save(topNewsImage);
    }

    public boolean saveMultipleTopNewsImage(MultipartFile[] imageFiles) throws IOException {
        for (MultipartFile imageFile: imageFiles) {
            saveTopNewsImage(imageFile);
        }

        return true;
    }
}
