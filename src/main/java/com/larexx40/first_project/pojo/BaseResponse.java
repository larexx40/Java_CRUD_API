package com.larexx40.first_project.pojo;

import lombok.Data;

@Data
public class BaseResponse {
    private int status;
    private String description;
    private Object data;

    //set default value  for base response
    public BaseResponse(){

    }

    //set default value  for base response when error is true;
    public BaseResponse(boolean error){
        //if error is true then set status code to 500
        this.status = 500;
        this.description = "An Error Occurred";
        this.data = null;
    }
}
