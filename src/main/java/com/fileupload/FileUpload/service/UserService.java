package com.fileupload.FileUpload.service;

import com.fileupload.FileUpload.model.UserModel;
import com.fileupload.FileUpload.repository.UserRepo;
import org.bson.types.ObjectId;
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

    public String update(String id, UserModel model) {
        ObjectId obj = new ObjectId(id);
        UserModel userExist = repo.findById(id);
        if(userExist!=null){
            userExist.setPassword(model.getPassword());
            repo.save(userExist);
            return "User updated successfully";
        }
        return "User not found";
    }
}
