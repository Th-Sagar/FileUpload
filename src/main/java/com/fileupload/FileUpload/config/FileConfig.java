package com.fileupload.FileUpload.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FileConfig {

    private final Dotenv dotenv = Dotenv.load();

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap<>();
        config.put("cloud_name",dotenv.get("CLOUDINARY_CLOUD_NAME"));
        config.put("api_key",dotenv.get("CLOUDINARY_API_KEY"));
        config.put("api_secret",dotenv.get("CLOUDINARY_API_SECRET"));
        config.put("secure",true);
        return new Cloudinary(config);
    }
}
