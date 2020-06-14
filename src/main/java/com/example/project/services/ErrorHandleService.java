package com.example.project.services;

import com.example.project.exception.ApplicationError;
import com.example.project.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//handling exception
@ControllerAdvice
@RestController
public class ErrorHandleService extends ResponseEntityExceptionHandler {
    //for obtaining the value of api_doc_url from application.properties and set in "details" variable
    @Value("${api_doc_url}")
    private String details;
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApplicationError> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest webRequest){
        ApplicationError error= new ApplicationError();
        error.setCode(101);
        error.setMessage(ex.getMessage());
        error.setDetails(details);
        return new ResponseEntity<ApplicationError>(error, HttpStatus.NOT_FOUND);
    }
}
