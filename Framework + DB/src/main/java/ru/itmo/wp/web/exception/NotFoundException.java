package ru.itmo.wp.web.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}