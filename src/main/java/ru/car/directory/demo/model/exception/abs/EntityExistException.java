package ru.car.directory.demo.model.exception.abs;

public abstract class EntityExistException extends Exception {
    public EntityExistException(String errorMessage) {
        super(errorMessage);
    }
}
