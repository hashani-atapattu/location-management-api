package com.authDoc.locationmanagementapi.validation;

import com.authDoc.locationmanagementapi.constant.ErrorType;
import com.authDoc.locationmanagementapi.exception.ErrorModel;
import com.authDoc.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<ErrorModel> validateRequest(UserModel userModel) {
        List<ErrorModel> errorList = new ArrayList<ErrorModel>();
        if(null != userModel && userModel.getEmail() == null){
            ErrorModel error = new ErrorModel();
            error.setMessage("Email can not be empty");
            error.setCode(ErrorType.NOT_EMPTY.toString());
            errorList.add(error);
        }

        if(null != userModel && userModel.getPassword() == null){
            ErrorModel error = new ErrorModel();
            error.setMessage("Password can not be empty");
            error.setCode(ErrorType.NOT_EMPTY.toString());
            errorList.add(error);
        }
        return errorList;
    }
}
