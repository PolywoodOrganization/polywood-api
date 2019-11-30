package com.polywood.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorObject {

    private Date date;
    private HttpStatus code;
    private String message;

    ErrorObject(Date date, HttpStatus code, String message) {
        this.date = date;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorObject{" +
                "date=" + date +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
