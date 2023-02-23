package ru.car.directory.demo.dao.impl;

import org.springframework.stereotype.Repository;
import ru.car.directory.demo.dao.abs.AbstractDaoImpl;
import ru.car.directory.demo.dao.abs.CarDao;
import ru.car.directory.demo.model.entity.Car;

import java.util.List;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<Long, Car> implements CarDao {

    @Override
    public List<Car> getAll() {
        return entityManager.createQuery("SELECT c FROM Car c", Car.class)
            .getResultList();
    }

    @Override
    public Boolean isExistByRegisterNumber(String registerNumber) {
        return entityManager.createQuery("SELECT c FROM Car c WHERE c.registerNumber = :registerNumber", Car.class)
            .setParameter("registerNumber", registerNumber)
            .getResultList().size() == 0;
    }
}
