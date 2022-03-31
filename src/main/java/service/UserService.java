package service;

import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

public class UserService extends GenericService<User,Integer>{
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    /*public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }*/

    public User findByUserName(String userName){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                User user = userRepository.findByUserName(userName);
                transaction.commit();
                return user;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
