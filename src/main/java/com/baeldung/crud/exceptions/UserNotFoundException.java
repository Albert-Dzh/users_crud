package com.baeldung.crud.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("Can't find user with id " + id);
    }
}
