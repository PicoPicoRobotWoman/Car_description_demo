package ru.car.directory.demo.dao.abs;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
public abstract class AbstractDaoImpl<PK, E> implements AbstractDao<PK, E> {

    private final Class<E> genericType;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractDaoImpl() {
        this.genericType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public void create(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public void deleteById(PK id) {
        entityManager.createQuery("DELETE FROM " + genericType.getName() + " t WHERE t.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.flush();
    }

    @Override
    public Optional<E> getById(PK id) {
        return Optional.ofNullable(entityManager.find(genericType, id));
    }

    @Override
    public boolean isExistById(PK id) {
        return entityManager.createQuery("SELECT COUNT (*) FROM " + genericType.getName() +
                        " t WHERE t.id=:id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }

}
