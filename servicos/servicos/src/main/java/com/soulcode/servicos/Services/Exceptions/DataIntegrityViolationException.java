package com.soulcode.servicos.Services.Exceptions;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String msg){
        super(msg);
    }
}
