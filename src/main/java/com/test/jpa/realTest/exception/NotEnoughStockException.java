package com.test.jpa.realTest.exception;

public class NotEnoughStockException extends RuntimeException{

    public NotEnoughStockException(String message){
        super(message);
    }
}
