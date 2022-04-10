package repository.impl;

import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.base.GenericRepository;

import java.io.Serializable;

public class GenericRepositoryImpl<T,ID extends Serializable> implements GenericRepository<T,ID> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private Class<T> clazz;

    public GenericRepositoryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public GenericRepositoryImpl() {
    }

    @Override
    public T add(T t) {
        var session = sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    @Override
    public T update(T t) {
        var session = sessionFactory.getCurrentSession();
        session.update(t);
        return t;
    }

    @Override
    public void delete(T t) {
        var session = sessionFactory.getCurrentSession();
        session.delete(t);
    }

    @Override
    public T findById(Class <T> tClazz,ID id) {
        var session = sessionFactory.getCurrentSession();
        return session
                .get(tClazz,id);
    }
}
