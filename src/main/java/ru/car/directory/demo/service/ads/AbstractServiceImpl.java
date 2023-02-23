package ru.car.directory.demo.service.ads;

import org.springframework.transaction.annotation.Transactional;
import ru.car.directory.demo.dao.abs.AbstractDao;
import ru.car.directory.demo.model.exception.CarExistException;

import java.util.List;
import java.util.Optional;

public abstract class AbstractServiceImpl<PK, E> implements AbstractService<PK, E> {

    private final AbstractDao<PK, E> abstractDao;

    public AbstractServiceImpl(AbstractDao<PK, E> abstractDao) {
        this.abstractDao = abstractDao;
    }

    @Override
    public List<E> getAll() {
        return abstractDao.getAll();
    }

    @Override
    public void create(E entity) throws CarExistException {
         abstractDao.create(entity);
    }

    @Override
    public void update(E entity) {
        abstractDao.update(entity);
    }

    @Override
    public void delete(E entity) {
        abstractDao.delete(entity);
    }

    @Override
    public void deleteById(PK id) {
        abstractDao.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<E> getById(PK id) {
        return abstractDao.getById(id);
    }

    @Override
    @Transactional
    public boolean isExistById(PK id) {
        return abstractDao.isExistById(id);
    }
}
