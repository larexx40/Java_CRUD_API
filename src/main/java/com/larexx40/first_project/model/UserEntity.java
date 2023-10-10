package com.larexx40.first_project.model;

import com.larexx40.first_project.pojo.UserData;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table (name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String firstName;
    private  String lastName;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private  String email;
    private String password;
//    @Temporal(javax.persistence.TemporalType.Date)
    private String dateOfBirth;

    //set default value  for user entity;
    public UserEntity(){

    }

    //initialize the class
    public UserEntity(UserData userData){
        this.firstName = userData.getFirstName();
        this.lastName = userData.getLastName();
        this.phoneNumber = userData.getPhoneNumber();
        this.email = userData.getEmail();
        this.password = userData.getPassword();
        this.dateOfBirth = userData.getDateOfBirth();
    }
}
