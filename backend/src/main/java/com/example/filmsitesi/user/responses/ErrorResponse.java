package com.example.filmsitesi.user.responses;

public class ErrorResponse extends Result {
    public ErrorResponse(){
        super(false);
    }
    public ErrorResponse(String message){
        super(false, message);
    }

}