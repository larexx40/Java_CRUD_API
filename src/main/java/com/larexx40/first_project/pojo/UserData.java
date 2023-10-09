package com.larexx40.first_project.pojo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.larexx40.first_project.config.AppConstant.*;

@Data
public class UserData {
    @NotEmpty
    @Pattern(regexp = NAME_VALIDATION_REGEX, message = "Firstname must be at least 3 characters")
    private  String firstName;
    @NotEmpty @Pattern(regexp = NAME_VALIDATION_REGEX, message = "Lastname must be at least 3 characters")
    private  String lastName;
    @Pattern(regexp = PHONENUMBER_VALIDATION_REGEX, message = PHONENUMBER_VALIDATION_ERROR_MESSAGE
    )
    private  String phoneNumber;
    @NotEmpty @Email(message = "Enter valid email address")
    private  String email;
    @NotEmpty @Pattern(regexp = PASSWORD_PATTERN, message="Password too weak ")
    private String password;
    @Pattern(regexp = DATE_OF_BIRTH_REGEX, message = DATE_OF_BIRTH_VALIDATION_MSG )
    private String dateOfBirth;
}
