package repository.impl;

import entity.Secretary;
import org.hibernate.SessionFactory;
import repository.SecretaryRepository;
import repository.SessionFactorySingleton;

public class SecretaryRepositoryImpl extends GenericRepositoryImpl<Secretary,Integer> implements SecretaryRepository {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public SecretaryRepositoryImpl() {
        super(Secretary.class);
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            session.createNativeQuery("TRUNCATE users CASCADE ", Secretary.class)
                    .executeUpdate();
            transaction.commit();
        }
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

