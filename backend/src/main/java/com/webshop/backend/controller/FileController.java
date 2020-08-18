package com.webshop.backend.controller;

import com.webshop.backend.dto.TopNewsImageDTO;
import com.webshop.backend.fileserrvice.FileService;
import com.webshop.backend.model.TopNewsImage;
import com.webshop.backend.service.TopNewsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class FileController {
    Logger logger = Logger.getLogger(FileController.class.getName());

    @Autowired
    private FileService fileService;

    @Autowired
    private TopNewsImageService topNewsImageService;

    @PostMapping("/image/upload")
    public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile imageFile ){
        try {
            fileService.saveImage(imageFile);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/image/upload/topNewsImage")
    public ResponseEntity uploadTopNewsImage(@RequestParam("imageFile") MultipartFile imageFile){
        try{
            topNewsImageService.saveTopNewsImage(imageFile);
        } catch (Exception e){
            logger.log(Level.WARNING, e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/image/upload/multiple/topNewsImages")
    public ResponseEntity uploadTopNewsImages(@RequestParam("imageFiles") MultipartFile[] imageFiles){
        try{
            topNewsImageService.saveMultipleTopNewsImage(imageFiles);
        } catch (Exception e){
            logger.log(Level.WARNING, e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/image/download/multiple/topNewsImage")
    public List<TopNewsImageDTO> sendBackTopNewsImage(){
        try {
            return topNewsImageService.getTopNewsImage();
        } catch (IOException ioException) {
            return null;
        }
    }
}
