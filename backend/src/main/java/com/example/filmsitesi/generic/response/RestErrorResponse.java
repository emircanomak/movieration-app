package com.example.filmsitesi.generic.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class RestErrorResponse {
    private Date errorDate;
    private String message;
    private String detail;

}
