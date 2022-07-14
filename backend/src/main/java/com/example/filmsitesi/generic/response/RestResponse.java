package com.example.filmsitesi.generic.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    private T data;
    private Date responseDate;
    private boolean isSuccess;
    private String message;

    public RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.responseDate = new Date();
    }
    public static <T> RestResponse<T> success(T t){
        return new RestResponse<>(t, true);
    }
    public static <T> RestResponse<T> error(T t){
        return new RestResponse<>(t, false);
    }
    public static <T> RestResponse<T> empty(){
        return new RestResponse<>(null, true);
    }
}
