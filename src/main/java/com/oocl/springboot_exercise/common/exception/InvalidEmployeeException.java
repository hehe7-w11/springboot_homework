package com.oocl.springboot_exercise.common.exception;

public class InvalidEmployeeException extends RuntimeException{
    public InvalidEmployeeException(String message){
        super(message);
    }
}
