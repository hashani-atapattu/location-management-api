package com.authDoc.locationmanagementapi.converter;

import com.authDoc.locationmanagementapi.entity.UserEntity;
import com.authDoc.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertModelToEntity(UserModel usermodel){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(usermodel.getEmail());
        userEntity.setFullName(usermodel.getFullName());
        userEntity.setMobileNumber(usermodel.getMobileNumber());
        userEntity.setPassword(usermodel.getPassword());
        return userEntity;
    }

    public UserModel convertEntityToModel(UserEntity userEntity){
        UserModel userModel = new UserModel();
        userModel.setEmail(userEntity.getEmail());
        userModel.setFullName(userEntity.getFullName());
        userModel.setMobileNumber(userEntity.getMobileNumber());
        userModel.setPassword(userEntity.getPassword());
        return userModel;
    }
}
