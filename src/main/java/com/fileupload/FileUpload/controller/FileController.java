package com.fileupload.FileUpload.controller;

import com.fileupload.FileUpload.model.UserModel;
import com.fileupload.FileUpload.service.FileService;
import com.fileupload.FileUpload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class FileController {


    @Autowired
    private FileService service;
    @Autowired
    private UserService userService;

    @PostMapping("/file-upload/{id}")
    public ResponseEntity<String> uploadImage(@RequestParam("image")MultipartFile file , @PathVariable String id){
        Map data = service.upload(file);
        String fileUrl = (String) data.get("url");
        UserModel userExist = userService.findById(id);
        if(userExist==null){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        if(userExist.getFileUrls()==null){
            userExist.setFileUrls(new ArrayList<>());
        }

        userExist.getFileUrls().add(fileUrl);

        userService.update(id,userExist);





        return new ResponseEntity<>("file uploaded and url added to user successfully", HttpStatus.OK);
    }



}
