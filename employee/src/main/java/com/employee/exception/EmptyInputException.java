package com.employee.exception;

import org.springframework.stereotype.Component;

@Component
public class EmptyInputException extends RuntimeException {

    private String errorCode;

    private String errorMessage;

    private String field;

    public EmptyInputException(String errorCode, String errorMessage, String field) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.field = field;
    }

    public EmptyInputException() {

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
