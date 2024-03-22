package org.rest.task3.exceptions;

public class APIException extends RuntimeException{
    private int statusCode;
    public APIException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
