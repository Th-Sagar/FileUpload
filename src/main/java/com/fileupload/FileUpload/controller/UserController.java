package com.fileupload.FileUpload.controller;

import com.fileupload.FileUpload.model.UserModel;
import com.fileupload.FileUpload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UserModel model){

        UserModel user = service.findByUserName(model.getUserName());
        if(user!=null){
            return new ResponseEntity<>("User already created",HttpStatus.BAD_REQUEST);


        }

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
        UserModel user = service.findById(id);
        if(user!=null){
            String msg = String.valueOf(service.update(id,model));
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> showOneUser(@PathVariable String id){
      UserModel user = service.findById(id);
      if(user!=null){
          return new ResponseEntity<>(user,HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        UserModel model = service.findById(id);
        if(model!=null){
            return new ResponseEntity<>(service.deleteOneUser(id),HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }
}
