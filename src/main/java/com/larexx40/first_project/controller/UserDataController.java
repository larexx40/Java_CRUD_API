package com.larexx40.first_project.controller;

import com.larexx40.first_project.pojo.BaseResponse;
import com.larexx40.first_project.pojo.UserData;
import com.larexx40.first_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserDataController {
    @Autowired
    UserService userService;
    @PostMapping ("/create_user")
    public ResponseEntity createUser (@Valid @RequestBody UserData userdata){
        BaseResponse baseResponse = userService.createUser(userdata);
        HttpStatus status = (baseResponse.getStatus() == 200)? HttpStatus.OK:  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse, status);
    }

    @PostMapping ("/update_user")
    public ResponseEntity updateUser (@Valid @RequestBody UserData userdata){
        BaseResponse baseResponse = userService.updateUser(userdata);
        HttpStatus status = (baseResponse.getStatus() == 200)? HttpStatus.OK:  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse, status);
    }

    @GetMapping("/")
    public  ResponseEntity getAllUser(){
        BaseResponse baseResponse = userService.getAllUsers();
        HttpStatus status = (baseResponse.getStatus() == 200)? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse, status);
    }

    @GetMapping("/get_user")
    public ResponseEntity getUserByEmail(@RequestParam("email") String email){
        BaseResponse baseResponse = userService.getUserByEmail(email);
        HttpStatus status = (baseResponse.getStatus() == 200)? HttpStatus.OK:  HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(baseResponse, status);
    }

    // General exception for the rest controller
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  BaseResponse handleValidationExceptions(MethodArgumentNotValidException ex){
        BaseResponse baseResponse = new BaseResponse(true);
        baseResponse.setStatus(400);
        baseResponse.setDescription("An Error Occurred");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        baseResponse.setData(errors);
        return  baseResponse;
    }
}
