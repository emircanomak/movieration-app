package com.example.filmsitesi.generic.exceptions;

import com.example.filmsitesi.generic.response.RestErrorResponse;
import com.example.filmsitesi.generic.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        String message = ex.getMessage();
        String detail = webRequest.getDescription(false);
        return getResponseEntity(message, detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleBusinessExceptions(BusinessException ex, WebRequest webRequest){
        String message = ex.getBaseErrorMessage().getMessage();
        String detail = webRequest.getDescription(false);
        return getResponseEntity(message, detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleItemNotFoundExceptions(ItemNotFoundException ex, WebRequest webRequest){
        String message = ex.getBaseErrorMessage().getMessage();
        String detail = webRequest.getDescription(false);
        return getResponseEntity(message, detail, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> getResponseEntity(String message, String detail, HttpStatus httpStatus){
        Date errorDate = new Date();
        RestErrorResponse restErrorResponse = new RestErrorResponse(errorDate, message, detail);
        RestResponse<RestErrorResponse> restResponse =  RestResponse.error(restErrorResponse);
        restResponse.setMessage(message);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(restResponse, httpStatus);
        return responseEntity;
    }
}
