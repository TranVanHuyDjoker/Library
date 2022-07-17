package com.example.librarycrud.config.exception;
import lombok.Data;

@Data
public class ErrorMessage {
    private int statusCode;

    private String message;

}