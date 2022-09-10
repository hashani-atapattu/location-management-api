package com.authDoc.locationmanagementapi.service;

import com.authDoc.locationmanagementapi.constant.ErrorType;
import com.authDoc.locationmanagementapi.converter.UserConverter;
import com.authDoc.locationmanagementapi.entity.UserEntity;
import com.authDoc.locationmanagementapi.exception.BusinessException;
import com.authDoc.locationmanagementapi.exception.ErrorModel;
import com.authDoc.locationmanagementapi.model.UserModel;
import com.authDoc.locationmanagementapi.repository.UserEntityRepository;
import com.authDoc.locationmanagementapi.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserEntityRepository entityRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean login(UserModel userModel) throws BusinessException{
        boolean result = false;

        List<ErrorModel> errorModels = userValidator.validateRequest(userModel);
        //check whether email and password is empty
        if(!CollectionUtils.isEmpty(errorModels)){
            throw new BusinessException(errorModels);
        }

        UserEntity userEntity = entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if(userEntity == null){
            List<ErrorModel> errorList = new ArrayList<ErrorModel>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("Incorrect email or password");
            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }else{
            result =true;
        }
        return result;
    }

    @Override
    public Long register(UserModel userModel) throws BusinessException {

        List<ErrorModel> errorModels = userValidator.validateRequest(userModel);

        //check whether email and password is empty
        if(!CollectionUtils.isEmpty(errorModels)){
            throw new BusinessException(errorModels);
        }
        UserEntity entity = userConverter.convertModelToEntity(userModel);
        //check user is already existing
        UserEntity ue = entityRepository.findByEmail(userModel.getEmail());
        if(null != ue){
            List<ErrorModel> errorList = new ArrayList<>();
            ErrorModel error = new ErrorModel();
            error.setMessage("User with this email already exists, try another email.");
            error.setCode(ErrorType.ALREADY_EXISTS.toString());
            errorList.add(error);
            throw new BusinessException(errorList);
        }

        UserEntity userEntity = entityRepository.save(entity);

        return userEntity.getId();
    }
}
