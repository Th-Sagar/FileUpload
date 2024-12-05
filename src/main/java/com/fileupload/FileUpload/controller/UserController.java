package com.fileupload.FileUpload.controller;

import com.fileupload.FileUpload.model.UserModel;
import com.fileupload.FileUpload.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UserModel model){
        String msg = String.valueOf(service.addUser(model));
        System.out.println(model.getUserName());
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    @GetMapping("/user")
    public ResponseEntity<List<UserModel>> showUser(){
        return new ResponseEntity<>(service.showAllUser(),HttpStatus.OK);
    }
    @PostMapping("/update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id,@RequestBody UserModel model){
       try {
           String msg = String.valueOf(service.update(id,model));
           return new ResponseEntity<>(msg,HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
       }

    }
}
