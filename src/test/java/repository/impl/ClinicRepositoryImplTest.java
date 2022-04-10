package repository.impl;

import entity.Clinic;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.SessionFactorySingleton;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicRepositoryImplTest {
    private static SessionFactory sessionFactory;
    private static ClinicRepositoryImpl clinicRepository;
    Clinic clinic = new Clinic("a",new HashSet<>());
    Clinic clinic1 = new Clinic("e",new HashSet<>());

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        clinicRepository = new ClinicRepositoryImpl();
    }

    @BeforeEach
    void truncate(){
        clinicRepository.truncate();
    }

    @Test
    void addTest(){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(clinic);
        assertNotNull(clinic.getName());
    }


    @Test
    void updateTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        clinic.setName("b");
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.update(clinic);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals("b",clinic.getName());
    }


    @Test
    void deleteTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Clinic returnedClinic = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.delete(clinic);
                returnedClinic = clinicRepository.findById(Clinic.class,clinic.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNull(returnedClinic);
    }

    @Test
    void findByIdTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Clinic returnedClinic = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                returnedClinic = clinicRepository.findById(Clinic.class,clinic.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(returnedClinic.getName());
        assertNotNull(returnedClinic);
    }


    @Test
    void findAllTest() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                clinicRepository.add(clinic1);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        List<Clinic> clinics = null;
        try (var session = sessionFactory.getCurrentSession()) {
            try {
                clinics = clinicRepository.findAll();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals(2,clinics.size());
        assertNotNull(clinics);
    }
}