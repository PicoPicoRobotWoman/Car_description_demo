package ru.car.directory.demo.service.ads;

import ru.car.directory.demo.model.exception.CarExistException;

import java.util.List;
import java.util.Optional;

public interface AbstractService<PK, E> {

    List<E> getAll();
    void create(E entity) throws CarExistException;
    void update(E entity);
    void delete(E entity);
    void deleteById(PK id);
    Optional<E> getById(PK id);
    boolean isExistById(PK id);

}
