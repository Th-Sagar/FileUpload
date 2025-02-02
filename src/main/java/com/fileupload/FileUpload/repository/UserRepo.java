package com.fileupload.FileUpload.repository;

import com.fileupload.FileUpload.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserModel, ObjectId> {
    boolean findAllById(ObjectId id);
    UserModel findById(String id);
    UserModel findByUserName(String userName);
    Optional<UserModel> findByEmail(String email);
}
