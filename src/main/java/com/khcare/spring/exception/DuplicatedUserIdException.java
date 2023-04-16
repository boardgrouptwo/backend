package com.khcare.spring.exception;

public class DuplicatedUserIdException  extends RuntimeException{
    public DuplicatedUserIdException() {
        super();
    }

    public DuplicatedUserIdException(String message) {
        super(message);
    }

    public DuplicatedUserIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedUserIdException(Throwable cause) {
        super(cause);
    }
}
