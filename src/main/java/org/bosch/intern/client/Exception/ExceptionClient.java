package org.bosch.intern.client.Exception;

public class ExceptionClient extends RuntimeException{
    private  String exceptionMessage;

    public ExceptionClient(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }
}
