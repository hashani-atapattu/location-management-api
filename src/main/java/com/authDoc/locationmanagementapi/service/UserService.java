package com.authDoc.locationmanagementapi.service;

import com.authDoc.locationmanagementapi.exception.BusinessException;
import com.authDoc.locationmanagementapi.model.UserModel;
import org.apache.catalina.User;

public interface UserService {

    public boolean login(UserModel userModel)throws BusinessException;

    public Long register(UserModel userModel)throws BusinessException;
}
