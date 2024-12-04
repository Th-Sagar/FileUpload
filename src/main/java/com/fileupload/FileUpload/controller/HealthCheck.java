package com.fileupload.FileUpload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/")
    public String healthCheck(){
        return "Your health is ok";
    }
}
