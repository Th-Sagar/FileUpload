package com.fileupload.FileUpload.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileService {

    @Autowired
    private Cloudinary cloudinary;

    public Map upload(MultipartFile file) {

        try{
            Map<String, Object> options = new HashMap<>();
            options.put("folder","Images");
            Map<String,Object> data = cloudinary.uploader().upload(file.getBytes(),options);

            System.out.println((String) data.get("url"));
            return data;
        }catch (IOException e){
            throw new RuntimeException("Image uploading fail!!");
        }
    }
}
