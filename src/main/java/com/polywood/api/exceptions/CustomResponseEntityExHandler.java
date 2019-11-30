package com.polywood.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ErrorObject>  handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorObject e = new ErrorObject(Date.from(Instant.now()), HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ResponseEntity<ErrorObject>(e, HttpStatus.UNAUTHORIZED);
    }
}
