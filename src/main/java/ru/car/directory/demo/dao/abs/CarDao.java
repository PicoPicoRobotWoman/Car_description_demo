package ru.car.directory.demo.dao.abs;

import ru.car.directory.demo.model.entity.Car;

public interface CarDao extends AbstractDao<Long, Car> {

   Boolean isExistByRegisterNumber(String registerNumber);

}
