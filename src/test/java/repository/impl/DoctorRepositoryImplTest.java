package repository.impl;

import entity.Clinic;
import entity.Doctor;
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

class DoctorRepositoryImplTest {
    private static SessionFactory sessionFactory;
    private static DoctorRepositoryImpl doctorRepository;
    private static ClinicRepositoryImpl clinicRepository;
    private static SecretaryRepositoryImpl secretaryRepository;
    Doctor doctor = new Doctor("a","1","a", UserType.DOCTOR,"a",null,new HashSet<>(),new HashSet<>(),new HashSet<>(),null);
    Clinic clinic = new Clinic("a",new HashSet<>());
    Secretary secretary = new Secretary("a","2","a",UserType.SECRETORY,new HashSet<>(),new HashSet<>());
    Doctor doctor1 = new Doctor("b","4","b", UserType.DOCTOR,"b",null,new HashSet<>(),new HashSet<>(),new HashSet<>(),null);
    Clinic clinic1 = new Clinic("b",new HashSet<>());
    Secretary secretary1 = new Secretary("b","3","b",UserType.SECRETORY,new HashSet<>(),new HashSet<>());

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        doctorRepository = new DoctorRepositoryImpl();
        clinicRepository = new ClinicRepositoryImpl();
        secretaryRepository = new SecretaryRepositoryImpl();
    }

    @BeforeEach
    void truncate(){
        doctorRepository.truncate();
        clinicRepository.truncate();
        secretaryRepository.truncate();
    }

    @Test
    void addTest(){
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);

        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                doctorRepository.add(doctor);
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(doctor);
        assertNotNull(doctor.getNationalCode());
    }


    @Test
    void updateTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                doctorRepository.add(doctor);
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        doctor.setFullName("b");
        doctor.setPassword("b");
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.update(doctor);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals("b",doctor.getFullName());
        assertEquals("b",doctor.getPassword());
    }


    @Test
    void deleteTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                doctorRepository.add(doctor);
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Doctor returnedDoctor = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.delete(doctor);
                returnedDoctor = doctorRepository.findById(Doctor.class,doctor.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNull(returnedDoctor);
    }

    @Test
    void findByIdTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                doctorRepository.add(doctor);
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Doctor returnedDoctor = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                returnedDoctor = doctorRepository.findById(Doctor.class,doctor.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(returnedDoctor.getFullName());
        assertNotNull(returnedDoctor);
    }


    @Test
    void findAllTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        doctor1.setClinic(clinic1);
        doctor1.setSecretary(secretary1);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                doctorRepository.add(doctor);
                secretaryRepository.add(secretary);
                clinicRepository.add(clinic1);
                doctorRepository.add(doctor1);
                secretaryRepository.add(secretary1);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        List<Doctor> doctors = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctors = doctorRepository.findAll();
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals(2, doctors.size());
        assertNotNull(doctors);
    }

    @Test
    void findByNationalCodeTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                clinicRepository.add(clinic);
                doctorRepository.add(doctor);
                secretaryRepository.add(secretary);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Doctor returnedDoctor = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                returnedDoctor = doctorRepository.findByNationalCode(doctor.getNationalCode());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(returnedDoctor);
    }
}