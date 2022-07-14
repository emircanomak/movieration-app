package com.example.filmsitesi.user.responses;



public class SuccessResponse extends Result{
    public SuccessResponse(){
        super(true);
    }
    public SuccessResponse(String message){
        super(true,message);
    }
}