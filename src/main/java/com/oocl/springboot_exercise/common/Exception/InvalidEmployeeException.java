package com.oocl.springboot_exercise.common.Exception;

public class InvalidEmployeeException extends RuntimeException{
    public InvalidEmployeeException(String message){
        super(message);
    }
}
