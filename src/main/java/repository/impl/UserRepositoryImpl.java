package repository.impl;

import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.UserRepository;

public class UserRepositoryImpl extends GenericRepositoryImpl<User,Integer> implements UserRepository {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public User findByUserName(String userName) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select u from User u where u.nationalCode = :userName",User.class)
                .setParameter("national_code",userName)
                .getSingleResult();
    }
}
