package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DepartmentIdNotFound extends RuntimeException {

    private HttpStatus errorCode;

    private String errorMessage;

    public DepartmentIdNotFound(HttpStatus errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public DepartmentIdNotFound() {

    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
