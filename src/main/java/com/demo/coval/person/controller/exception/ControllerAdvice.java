package com.demo.coval.person.controller.exception;

import com.demo.coval.person.dto.ErrorExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExceptionDTO> requestExceptionHandler(RequestException ex) {
        return getErrorExceptionDTOResponseEntity(ex.getCode(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorExceptionDTO> serviceExceptionHandler(ServiceException ex) {
        return getErrorExceptionDTOResponseEntity(ex.getCode(), ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorExceptionDTO> personNotFoundException(PersonNotFoundException ex) {
        return getErrorExceptionDTOResponseEntity(ex.getCode(), ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(value = PersonParamIsNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorExceptionDTO> personParamIsNotValidExeption(PersonParamIsNotValidException ex) {
        return getErrorExceptionDTOResponseEntity(ex.getCode(), ex.getMessage(), ex.getStatus());
    }

    private static ResponseEntity<ErrorExceptionDTO> getErrorExceptionDTOResponseEntity(String ex, String ex1, HttpStatus ex2) {
        ErrorExceptionDTO error = ErrorExceptionDTO.builder().code(ex).message(ex1).build();
        return new ResponseEntity<>(error, ex2);
    }
}
