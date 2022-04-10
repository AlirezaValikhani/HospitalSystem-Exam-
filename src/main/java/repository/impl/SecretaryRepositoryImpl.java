package repository.impl;

import entity.Secretary;
import org.hibernate.SessionFactory;
import repository.SecretaryRepository;
import repository.SessionFactorySingleton;

import java.util.List;

public class SecretaryRepositoryImpl extends GenericRepositoryImpl<Secretary,Integer> implements SecretaryRepository {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public SecretaryRepositoryImpl() {
        super(Secretary.class);
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            transaction.begin();
            session.createNativeQuery("TRUNCATE users CASCADE ", Secretary.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public List<Secretary> findAll() {
            var session = sessionFactory.getCurrentSession();
            var transaction = session.beginTransaction();
            List<Secretary> secretaries = session
                    .createQuery("select s from Secretary s",Secretary.class)
                    .list();
            transaction.commit();
            return secretaries;
    }

    @Override
    public Secretary findByNationalCode(String nationalCode) {
            var session = sessionFactory.getCurrentSession();
            return session
                    .createQuery("select s from Secretary s where s.nationalCode = :nationalCode",Secretary.class)
                    .setParameter("nationalCode",nationalCode)
                    .getSingleResult();
        }
    }

