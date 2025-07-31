package io.devotel.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }

    public UserNotFoundException(Long id, Throwable cause) {
        super("user with id " + id + " not found", cause);
    }
}