package com.example.filmsitesi.user.service;

import com.example.filmsitesi.user.dtos.UserRequest;
import com.example.filmsitesi.user.entities.User;
import com.example.filmsitesi.user.repositories.UserRepository;
import com.example.filmsitesi.user.responses.ErrorResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;


    public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        public User saveOneUser(UserRequest newUser) {
            User user = new User();
            user.setFullname(newUser.getFullname());
            user.setUserName(newUser.getUserName());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        }

        public String deleteAll() {
            userRepository.deleteAll();
            return "All clear!";
        }

        public void deleteById(Long userId) {
            try {
                userRepository.deleteById(userId);
            }catch(EmptyResultDataAccessException e) { //user zaten yok, db'den empty result gelmiş
                new ResponseEntity(new ErrorResponse("User doesnt exist!"), HttpStatus.NOT_FOUND);

            }
        }

        public Optional<User> getOneUserByUserName(String username){
            return userRepository.findByUserName(username);

        }
    public boolean validateUserRegister(UserRequest userRequest){

        Optional<User> user = userRepository.findByUserName(userRequest.getUserName());
        return user.isPresent();
    }

    public boolean validateUserLogin(UserRequest userLoginRequest){

        Optional<User> user = userRepository.findByUserName(userLoginRequest.getUserName());
        if(user.isPresent()) {
            String pass = user.get().getPassword();
            String username = user.get().getUserName();
            if( username.equalsIgnoreCase(userLoginRequest.getUserName()) && pass.matches(userLoginRequest.getPassword()) ){
                return true;
            }
        } return false;

    }



}
