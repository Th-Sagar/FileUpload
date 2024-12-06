package com.fileupload.FileUpload.controller;

import com.fileupload.FileUpload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class FileController {


    @Autowired
    private FileService service;

    @PostMapping("/file-upload")
    public ResponseEntity<Map> uploadImage(@RequestParam("image")MultipartFile file){
        Map data = service.upload(file);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }



}
