package com.employee.advice;

import com.employee.exception.DepartmentIdNotFound;
import com.employee.exception.EmptyInputException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice {

    Logger logs = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException) {
        logs.error("Inside handleEmpty Input. Field missing " + emptyInputException.getField());
        return new ResponseEntity<String>("Please check input values. Value of field is missing that is " + emptyInputException.getField(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentIdNotFound.class)
    public ResponseEntity<String> handleDepartmentIdNotFound(DepartmentIdNotFound departmentIdNotFound) {
        logs.error("Inside handleDepartmentIdNotFound. Id is");
        return new ResponseEntity<>("id not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noValuePresent(NoSuchElementException noSuchElementException) {
        logs.error("Value does not exist in db");
        return new ResponseEntity<>("Value does not exist in DB. Please enter the correct value", HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullValuePresent(NullPointerException nullPointerException) {
        logs.error("Inside novaluePresent method");
        return new ResponseEntity<>("Value is missing for one or more fields check. Please enter the correct value", HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> methodDoesNotExist(HttpRequestMethodNotSupportedException methodNotAllowed) {
        logs.error("Method does not exist for api call");
        return new ResponseEntity<>("Method is not defined for this api", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> internalServerError(HttpServerErrorException.InternalServerError ex) {
        logs.error("Inside internal Server error method");
        ex.printStackTrace();
        return new ResponseEntity<>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<String> unauthorizedRequest(HttpClientErrorException.Unauthorized unauthorized) {
        logs.error("In unauthorizedRequest method");
        return new ResponseEntity<>("Unauthorized access to REST call", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<String> forbiddenRequest(HttpClientErrorException.Forbidden forbidden) {
        logs.error("Inside forbidden request method");
        return new ResponseEntity<>("Forbidden request", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> uniqueConstraintViolation(ConstraintViolationException constraintViolationException) {
        logs.error("Inside uniqueConstraintViolation");
        return new ResponseEntity<>("Data is already present. Enter new one", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> recordAlreadyPresent(DataIntegrityViolationException dataIntegrityViolationException) {
        logs.error("record already present");
        return new ResponseEntity<>("Record already present . Enter new one", HttpStatus.BAD_REQUEST);
    }
}
