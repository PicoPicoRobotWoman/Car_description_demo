package ru.car.directory.demo.model.exception.abs;

public abstract class EntityNonExistException extends Exception {
    public EntityNonExistException(String errorMessage) {
        super(errorMessage);
    }
}
