package repository.impl;

import entity.Secretary;
import entity.UserType;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.SessionFactorySingleton;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryImplTest {
    private static SessionFactory sessionFactory;
    private static SecretaryRepositoryImpl adminRepositoryimpl;
    private static PatientRepositoryImpl patientRepositoryimpl;
    private static AppointmentRepositoryImpl appointmentRepositoryImpl;

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        adminRepositoryimpl = new SecretaryRepositoryImpl();
        patientRepositoryimpl = new PatientRepositoryImpl();
        appointmentRepositoryImpl = new AppointmentRepositoryImpl();
    }

    @Test
    void addTest(){
        Secretary secretary = new Secretary("a","1","a", UserType.SECRETORY,new HashSet<>(),new HashSet<>());

        Secretary returnedAdmin = adminRepositoryimpl.add(secretary);

        assertNotNull(secretary);
        /*assertEquals(secretary,returnedAdmin.getFullName());
        assertEquals(secretary,returnedAdmin.getPassword());*/
    }



    @AfterEach
    void truncate(){
        adminRepositoryimpl.truncate();
    }
}