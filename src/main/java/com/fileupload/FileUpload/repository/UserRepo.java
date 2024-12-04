package com.fileupload.FileUpload.repository;

import com.fileupload.FileUpload.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends MongoRepository<UserModel, ObjectId> {
}
