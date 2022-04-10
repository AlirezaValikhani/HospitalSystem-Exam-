package repository.impl;

import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.SessionFactorySingleton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {
    private static SessionFactory sessionFactory;
    private static UserRepositoryImpl userRepository;
    User user = new User("a","1","a", null);
    User user1 = new User("b","2","b", null);

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        userRepository = new UserRepositoryImpl();
    }

    @BeforeEach
    void truncate(){
        userRepository.truncate();
    }

    @Test
    void addTest(){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                userRepository.add(user);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(user);
        assertNotNull(user.getFullName());
    }


    @Test
    void updateTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                userRepository.add(user);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        user.setFullName("b");
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                userRepository.update(user);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals("b",user.getFullName());
    }


    @Test
    void deleteTest() {
        //چون یوزری قرار نیست داشته باشیم دیلیتم براش نیاز نیست
    }

    @Test
    void findByIdTest() {
       //چون entity نیست پس آی دی ام نداره که بخواییم فایند کنیم
    }


    @Test
    void findAllTest() {

        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                userRepository.add(user);
                userRepository.add(user1);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        List<User> users = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                users = userRepository.findAll();
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals(2, users.size());
        assertNotNull(users);
    }
}