package com.fileupload.FileUpload.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileService {

    @Autowired
    private Cloudinary cloudinary;

    public Map upload(MultipartFile file) {
        try{
            Map<String, Object> options = new HashMap<>();
            options.put("folder","Images");
            Map<String,Object> data = cloudinary.uploader().upload(file.getBytes(),options);


            return data;
        }catch (IOException e){
            throw new RuntimeException("Image uploading fail!!");
        }
    }

    public void removeImage(String fileUrl) {
        try{
            String publicId = extractPublicIdFromUrl(fileUrl);
            System.out.println(publicId);
            if(publicId==null || publicId.isEmpty()){
                throw new RuntimeException("Could not extract public_id from url");
            }
            Map<String,Object> options = new HashMap<>();
            options.put("invalidate",true);
            Map<String,Object> result = cloudinary.uploader().destroy(publicId,options);
            if(result!=null && result.get("result").equals("ok")){
                System.out.println("Image deleted successfully");
            }else {
                System.out.println("Failed to delete images:"+ result);
            }
        }catch (IOException e){
            throw  new RuntimeException("Image Deletion failed",e);
        }

    }


    private String extractPublicIdFromUrl(String fileUrl){
        try{
            URL url = new URL(fileUrl);
            String path = url.getPath();
            Pattern pattern = Pattern.compile(".*/upload/v[0-9]+/(.*)\\.[^/.]+$");
            Matcher matcher = pattern.matcher(path);
            if(matcher.find()){
                return matcher.group(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
