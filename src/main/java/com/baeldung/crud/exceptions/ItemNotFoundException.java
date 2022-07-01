package com.baeldung.crud.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(int id) {
        super("Can't find item with id " + id);
    }
}
