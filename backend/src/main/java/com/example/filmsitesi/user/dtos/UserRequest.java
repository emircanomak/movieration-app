package com.example.filmsitesi.user.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {

    String fullname;

    String userName;

    String password;


}