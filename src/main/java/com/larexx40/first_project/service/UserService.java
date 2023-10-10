package com.larexx40.first_project.service;

import com.larexx40.first_project.model.UserEntity;
import com.larexx40.first_project.pojo.BaseResponse;
import com.larexx40.first_project.pojo.UserData;
import com.larexx40.first_project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    private static final  Logger LOG = Logger.getLogger(UserService.class.getName());
    @Autowired
    UserRepo userRepo;
    public BaseResponse createUser(UserData userData){
        BaseResponse baseResponse = new BaseResponse(true );

        try {
            UserEntity userEntity = new UserEntity(userData);
            userRepo.save(userEntity);
            baseResponse.setStatus(200);
            baseResponse.setDescription("User Created");
            baseResponse.setData(userData);
        }catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }
}
