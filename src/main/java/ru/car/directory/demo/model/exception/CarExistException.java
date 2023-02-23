package ru.car.directory.demo.model.exception;

import ru.car.directory.demo.model.exception.abs.EntityExistException;

public class CarExistException extends EntityExistException {
    public CarExistException(String errorMessage) {
        super(errorMessage);
    }
}
