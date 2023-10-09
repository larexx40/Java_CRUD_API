package com.larexx40.first_project.service;

import com.larexx40.first_project.pojo.BaseResponse;
import com.larexx40.first_project.pojo.UserData;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    private static final  Logger LOG = Logger.getLogger(UserService.class.getName());
    public BaseResponse createUser(UserData userData){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(500);
        baseResponse.setDescription("An Error Occurred");

        try {
            if(userData.getEmail()  != null && userData.getPassword() != null ){
                baseResponse.setStatus(200);
                baseResponse.setDescription("Successful");
            }
        }catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }
}
