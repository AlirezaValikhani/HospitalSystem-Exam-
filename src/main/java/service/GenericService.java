package service;

import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.GenericRepositoryImpl;

import java.io.Serializable;
import java.util.List;

public class GenericService<T,ID extends Serializable> {
    private GenericRepositoryImpl<T,ID> genericRepositoryImpl = new GenericRepositoryImpl<>();
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public T add(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepositoryImpl.add(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public void delete(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepositoryImpl.delete(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    public void update(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepositoryImpl.update(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    public T findById(Class<T> tClass,ID id){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                T t = genericRepositoryImpl.findById(tClass,id);
                transaction.commit();
                return t;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
