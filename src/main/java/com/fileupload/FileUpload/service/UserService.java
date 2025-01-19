package com.fileupload.FileUpload.service;

import com.fileupload.FileUpload.model.UserModel;
import com.fileupload.FileUpload.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public String addUser(UserModel model) {
        UserModel userExist = repo.findByUserName(model.getUserName());
        if(userExist!=null){
            return "User is already existed";
        }

        model.setPassword(encoder.encode(model.getPassword()));
        repo.save(model);
        return "User is created Successfully";
    }
    public List<UserModel> showAllUser(){
        return repo.findAll();
    }

    public String update(String id, UserModel model) {
        UserModel userExist = repo.findById(id);
            userExist.setPassword(encoder.encode(model.getPassword()));
            userExist.setFileUrls(model.getFileUrls());
            repo.save(userExist);
            return "User updated successfully";
    }
    public UserModel findById(String id) {
        return repo.findById(id);
    }

    public UserModel findByUserName(String userName) {
        return repo.findByUserName(userName);
    }

    public String deleteOneUser(String id) {
        ObjectId objectId = new ObjectId(id);
        repo.deleteById(objectId);
        return "User deleted ";
    }

    public String removeArray(String id, String fileUrl) {
        UserModel userExist = repo.findById(id);

        if(userExist!=null){
            List<String> fileUrls= userExist.getFileUrls();
            if(fileUrls!=null && fileUrls.contains(fileUrl)){
                fileUrls.remove(fileUrl);
                userExist.setFileUrls(fileUrls);
                repo.save(userExist);
                return "URL removed successfully";
            }else {
                return "URL not found in the user's fileUrls";
            }
        }
        return "User not found";
    }

    public String verifyUser(UserModel model) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(model.getUserName(),model.getPassword()));

        Optional<UserModel> userExiste = repo.findByEmail(model.getEmail());

        if(authentication.isAuthenticated() && userExiste.isPresent()){
            return jwtService.generateToken(model.getUserName());
        }
        return null;

    }
}
