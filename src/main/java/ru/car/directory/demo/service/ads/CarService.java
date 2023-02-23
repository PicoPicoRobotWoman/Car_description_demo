package ru.car.directory.demo.service.ads;

import ru.car.directory.demo.model.entity.Car;
import ru.car.directory.demo.model.exception.CarExistException;
import ru.car.directory.demo.model.exception.CarNonExistException;

import java.util.List;
import java.util.Map;

public interface CarService extends AbstractService<Long, Car> {
    void save(Car car) throws CarExistException;
    Map<String, String> getMetaDate(List<String> metaDates) throws CarNonExistException;
    void delete(Long id) throws CarNonExistException;
    List<Car> getCars();

}
