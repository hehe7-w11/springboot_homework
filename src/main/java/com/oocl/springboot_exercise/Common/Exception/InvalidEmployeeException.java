package com.oocl.springboot_exercise.Common.Exception;

public class InvalidEmployeeException extends RuntimeException{
    public InvalidEmployeeException(String message){
        super(message);
    }
}
