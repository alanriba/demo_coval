package com.demo.coval.person.controller.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class PersonParamIsNotValidException extends RuntimeException {
    private String code;
    private HttpStatus status;

    public PersonParamIsNotValidException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
