package com.example.filmsitesi.user.controller;




import com.example.filmsitesi.user.dtos.UserResponseDto;
import com.example.filmsitesi.user.responses.ErrorResponse;
import com.example.filmsitesi.user.entities.User;
import com.example.filmsitesi.user.dtos.UserRequest;
import com.example.filmsitesi.user.responses.Result;
import com.example.filmsitesi.user.responses.SuccessResponse;
import com.example.filmsitesi.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {
    private  UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody UserRequest loginRequest) {
        Optional<User> usershadow=userService.getOneUserByUserName(loginRequest.getUserName());
        boolean isLoginSuccessful = userService.validateUserLogin(loginRequest);

        if(isLoginSuccessful){
            if(usershadow.isPresent()) {
            UserResponseDto user =new UserResponseDto();
            user.setUserName(usershadow.get().getUserName());
            user.setFullname(usershadow.get().getFullname());
            user.setPassword(usershadow.get().getPassword());
           return new ResponseEntity<>(new SuccessResponse("User has login succesfully"), HttpStatus.OK);
            //true döndürsün done
            //kullanıcı bilgilerini ver (comment kısmı,like kısmı) done
            }else{return new ResponseEntity<>(new ErrorResponse("User not present."), HttpStatus.BAD_REQUEST);}
        }
        else {
            //false döndürsün done
            return new ResponseEntity<>(new ErrorResponse("Wrong Password or Username."), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@RequestBody UserRequest registerRequest) {
        boolean userRegistered = userService.validateUserRegister(registerRequest);
        if(!userRegistered) {
            UserRequest user = new UserRequest();
            user.setUserName(registerRequest.getUserName());
            user.setFullname(registerRequest.getFullname());
            user.setPassword((registerRequest.getPassword()));
            userService.saveOneUser(user);
            return new ResponseEntity<>(new SuccessResponse("Username succesfully registered"),HttpStatus.OK);
        }
            else {
                //false döndürsün done
                return new ResponseEntity<>(new ErrorResponse("Username has already taken."), HttpStatus.BAD_REQUEST);
            }

    }

    @DeleteMapping("/delete")
    public void delete() {
        userService.deleteAll();

    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);

    }
    @GetMapping("/all")
    public  ResponseEntity<Result> getAllUsers() {
        if(userService.getAllUsers().isEmpty()){
            return new ResponseEntity<>(new ErrorResponse("No members found to display"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

}
