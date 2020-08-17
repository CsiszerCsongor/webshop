package com.webshop.backend.fileserrvice;

import com.webshop.backend.constants.ImageExtension;
import com.webshop.backend.exceptions.SaveImageException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${photo.directory.path}")
    private String photoDirectoryPath;

    private String saveImage(MultipartFile fileImage, String directoryPath) throws IOException {
        String extension = FilenameUtils.getExtension(fileImage.getOriginalFilename());
        if(!EnumUtils.isValidEnum(ImageExtension.class, extension)){
            throw new IOException("File is not image! File: " + fileImage.getOriginalFilename());
        }

        String uniqueFileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(16), extension);

        Path filepath = Paths.get(directoryPath, uniqueFileName);
        while(new File(filepath.toString()).exists()){
            filepath = Paths.get(directoryPath, uniqueFileName);
        }

        try{
            byte[] content = fileImage.getBytes();
            Files.write(filepath, content);
        } catch(IOException e){
            throw new SaveImageException("Can't save file! Error : " + e.getMessage());
        }

        return filepath.toString();
    }

    public String saveImageIntoDirectory(MultipartFile imageFile, String directoryPath) throws IOException {
        return saveImage(imageFile, directoryPath);
    }

    public String saveImage(MultipartFile imageFile) throws IOException {
        return saveImage(imageFile, this.photoDirectoryPath);
    }

}
