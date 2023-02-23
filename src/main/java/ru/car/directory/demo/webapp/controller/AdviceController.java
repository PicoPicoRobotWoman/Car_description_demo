package ru.car.directory.demo.webapp.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.car.directory.demo.model.dto.response.ErrorResponse;
import ru.car.directory.demo.model.exception.abs.EntityExistException;
import ru.car.directory.demo.model.exception.abs.EntityNonExistException;

@Slf4j
@ControllerAdvice
@ResponseBody
public class AdviceController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse globalException(Exception exception) {
        String msg = StackWalker
            .getInstance()
            .walk(s -> s.map(stackFrame -> stackFrame.getClassName() + " / " + stackFrame.getMethodName()).limit(10).toString());
        log.error(msg, exception, exception.getCause());
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(EntityNonExistException.class)
    public ErrorResponse EntityNonExistException(EntityNonExistException exception) {
        log.error("object(s) non exist", exception);
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityExistException.class)
    public ErrorResponse EntityExistException(EntityExistException exception) {
        log.error("object(s) exist", exception);
        return new ErrorResponse(exception.getMessage());
    }

}
