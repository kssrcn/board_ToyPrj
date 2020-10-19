package com.example.board.exception;

public class DuplicatedNickNameException extends RuntimeException {
    public DuplicatedNickNameException() {
        super();
    }

    public DuplicatedNickNameException(String message) {
        super(message);
    }

    public DuplicatedNickNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedNickNameException(Throwable cause) {
        super(cause);
    }

}
