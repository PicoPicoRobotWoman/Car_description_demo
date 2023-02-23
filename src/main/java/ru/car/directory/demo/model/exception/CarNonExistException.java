package ru.car.directory.demo.model.exception;

import ru.car.directory.demo.model.exception.abs.EntityNonExistException;

public class CarNonExistException extends EntityNonExistException {
    public CarNonExistException(String errorMessage) {
        super(errorMessage);
    }
}
