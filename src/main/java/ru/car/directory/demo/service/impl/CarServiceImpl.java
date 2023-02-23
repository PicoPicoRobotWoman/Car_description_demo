package ru.car.directory.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.car.directory.demo.dao.abs.CarDao;
import ru.car.directory.demo.model.entity.Car;
import ru.car.directory.demo.model.exception.CarExistException;
import ru.car.directory.demo.model.exception.CarNonExistException;
import ru.car.directory.demo.service.ads.AbstractServiceImpl;
import ru.car.directory.demo.service.ads.CarService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl extends AbstractServiceImpl<Long, Car> implements CarService {

    private final CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao) {
        super(carDao);
        this.carDao = carDao;
    }

    @Override
    @Transactional
    public void save(Car car) throws CarExistException {

        if (car.getId() != null && super.isExistById(car.getId())) throw new CarExistException("car with this param exist");
        if (car.getRegisterNumber() != null && !carDao.isExistByRegisterNumber(car.getRegisterNumber())) throw new CarExistException("car with this param exist");
        super.create(car);

    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> getMetaDate(List<String> metaDates) throws CarNonExistException {

        Map<String, String> map = new HashMap<>();
        List<Car> cars = super.getAll();

        if (metaDates.contains("first_entry")) {
            map.put("first_entry", cars.stream()
                .map(car -> {
                    System.out.println(car.getMetaDate().getDateCreation());
                    return car.getMetaDate().getDateCreation();
                })
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new CarNonExistException("Cars non exist"))
                .toString());
        }

        if (metaDates.contains("last_entry")) {
            map.put("last_entry", cars.stream()
                .map(car -> {
                    System.out.println(car.getMetaDate().getDateCreation());
                    return car.getMetaDate().getDateCreation();
                })
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new CarNonExistException("Cars non exist"))
                .toString());
        }

        if (metaDates.contains("count_entry")) {
            map.put("count_entry", cars.size() + "");
        }

        return map;

    }

    @Override
    @Transactional
    public void delete(Long id) throws CarNonExistException {
        if (!carDao.isExistById(id)) throw new CarNonExistException("car exist with id = " + id);
        carDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<Car> getCars() {
        return carDao.getAll();
    }

}
