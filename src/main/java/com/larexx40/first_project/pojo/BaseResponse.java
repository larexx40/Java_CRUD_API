package com.larexx40.first_project.pojo;

import lombok.Data;

@Data
public class BaseResponse {
    private int status;
    private String description;
    private Object data;
}
