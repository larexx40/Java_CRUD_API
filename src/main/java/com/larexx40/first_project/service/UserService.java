package com.larexx40.first_project.service;

import com.larexx40.first_project.model.UserEntity;
import com.larexx40.first_project.pojo.BaseResponse;
import com.larexx40.first_project.pojo.UserData;
import com.larexx40.first_project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.larexx40.first_project.config.AppConstant.ERROR_STATUS_CODE;
import static com.larexx40.first_project.config.AppConstant.SUCCESS_STATUS_CODE;

@Service
public class UserService {
    private static final  Logger LOG = Logger.getLogger(UserService.class.getName());
    @Autowired
    UserRepo userRepo;
    public BaseResponse createUser(UserData userData){
        BaseResponse baseResponse = new BaseResponse(true );

        try {
            //first check if user already exist
            Optional<UserEntity> isExist = userRepo.getUserByEmail(userData.getEmail());
            if (isExist.isPresent()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setDescription("User already exists");
                baseResponse.setData(null);
                return baseResponse;
            }

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
    public  BaseResponse getUserByEmail(String email) {
        BaseResponse baseResponse = new BaseResponse(true);
        try {
            if(email.isEmpty()){
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setDescription("Email cannot be empty");
                baseResponse.setData(null);
                return baseResponse;
            }
            Optional<UserEntity> user = userRepo.getUserByEmail(email);
            if(!user.isPresent()){
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setDescription("User With Email Not Found");
                baseResponse.setData(null);
                return baseResponse;
            }

            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setDescription("User Found");
            baseResponse.setData(user);
        }catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse getAllUsers(){
        BaseResponse baseResponse = new BaseResponse(true);
        try {
            List<UserEntity> users = userRepo.findAll();
            if(users.isEmpty()){
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setDescription("No User Found");
                baseResponse.setData(null);
                return baseResponse;
            }
            baseResponse.setStatus(SUCCESS_STATUS_CODE);
            baseResponse.setDescription("Users Found");
            baseResponse.setData(users);
        }catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }
    public BaseResponse updateUser(UserData userData){
        BaseResponse baseResponse = new BaseResponse(true );

        try {
            //first check if user already exist
            Optional<UserEntity> isExist = userRepo.getUserByEmail(userData.getEmail());
            if (!isExist.isPresent()) {
                baseResponse.setStatus(ERROR_STATUS_CODE);
                baseResponse.setDescription("User not found");
                baseResponse.setData(null);
                return baseResponse;
            }
            UserEntity userDetails = isExist.get();
            Long id = userDetails.getId();
            UserEntity newUserDetails = new UserEntity(userData);
            newUserDetails.setId(id);
            userRepo.save(newUserDetails);

            baseResponse.setStatus(200);
            baseResponse.setDescription("User Updated");
            baseResponse.setData(userData);
        }catch (Exception ex){
            LOG.warning(ex.getMessage());
        }
        return baseResponse;
    }
}
