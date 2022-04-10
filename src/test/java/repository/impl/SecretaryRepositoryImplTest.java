package repository.impl;

import entity.Secretary;
import entity.UserType;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.SessionFactorySingleton;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SecretaryRepositoryImplTest {
    private static SessionFactory sessionFactory;
    private static SecretaryRepositoryImpl secretaryRepository;
    private static PatientRepositoryImpl patientRepositoryimpl;
    private static AppointmentRepositoryImpl appointmentRepositoryImpl;
    Secretary secretary = new Secretary("a","1","a", UserType.SECRETORY,new HashSet<>(),new HashSet<>());
    Secretary secretary1 = new Secretary("b", "2", "b", UserType.SECRETORY, new HashSet<>(), new HashSet<>());


    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        secretaryRepository = new SecretaryRepositoryImpl();
        patientRepositoryimpl = new PatientRepositoryImpl();
        appointmentRepositoryImpl = new AppointmentRepositoryImpl();
    }

    @BeforeEach
    void truncate(){
        secretaryRepository.truncate();
    }

    @Test
    void addTest(){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(secretary);
        assertNotNull(secretary.getFullName());
    }


    @Test
    void updateTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        secretary.setFullName("b");
        secretary.setPassword("b");
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.update(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals("b",secretary.getFullName());
        assertEquals("b",secretary.getPassword());
    }


    @Test
    void deleteTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Secretary returnedSecretary = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.delete(secretary);
                returnedSecretary = secretaryRepository.findById(Secretary.class,secretary.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNull(returnedSecretary);
    }

    @Test
    void findByIdTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Secretary returnedSecretary = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                returnedSecretary = secretaryRepository.findById(Secretary.class,secretary.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(returnedSecretary.getNationalCode());
        assertNotNull(returnedSecretary);
    }


    @Test
    void findAllTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.add(secretary);
                secretaryRepository.add(secretary1);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        List<Secretary> secretaries = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaries = secretaryRepository.findAll();
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals(2,secretaries.size());
        assertNotNull(secretaries);
    }


    @Test
    void findByNationalCodeTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Secretary returnedSecretary = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                returnedSecretary = secretaryRepository.findByNationalCode(secretary.getNationalCode());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(returnedSecretary);
    }
}