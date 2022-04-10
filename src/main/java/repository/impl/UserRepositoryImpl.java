package repository.impl;

import entity.UserType;
import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl extends GenericRepositoryImpl<User,Integer> implements UserRepository {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public List<User> findAll() {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select u from User u", User.class)
                .list();
    }

    @Override
    public User findByUserName(String userName) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select u from User u where u.nationalCode = :userName",User.class)
                .setParameter("userName",userName)
                .getSingleResult();
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            transaction.begin();
            session.createNativeQuery("TRUNCATE users CASCADE ", User.class)
                    .executeUpdate();
            transaction.commit();
        }
    }
}
