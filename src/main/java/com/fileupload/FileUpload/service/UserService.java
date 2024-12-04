package com.fileupload.FileUpload.service;

import com.fileupload.FileUpload.model.UserModel;
import com.fileupload.FileUpload.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public String addUser(UserModel model) {
        repo.save(model);
        return "User is created Successfully";
    }
    public List<UserModel> showAllUser(){
        return repo.findAll();
    }

}
