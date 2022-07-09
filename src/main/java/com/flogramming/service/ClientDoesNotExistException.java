package com.flogramming.service;

public class ClientDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientDoesNotExistException() {
        super("Client Does Not Exist!");
    }
}
