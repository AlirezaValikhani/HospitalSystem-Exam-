package service;

import entity.Appointment;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.SessionFactorySingleton;
import repository.impl.SecretaryRepositoryImpl;
import repository.impl.AppointmentRepositoryImpl;
import repository.impl.DoctorRepositoryImpl;
import repository.impl.PatientRepositoryImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppointmentServiceTest {
    private static SessionFactory sessionFactory;
    private static DoctorRepositoryImpl doctorRepositoryImpl;
    private static PatientRepositoryImpl patientRepositoryimpl;
    private static SecretaryRepositoryImpl adminRepositoryimpl;
    private static AppointmentRepositoryImpl appointmentRepositoryImpl;

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        doctorRepositoryImpl = new DoctorRepositoryImpl();
        patientRepositoryimpl = new PatientRepositoryImpl();
        adminRepositoryimpl = new SecretaryRepositoryImpl();
        appointmentRepositoryImpl = new AppointmentRepositoryImpl();
    }

    @Test
    void addTest(){
        Appointment appointment = new Appointment(new Date(),true,null,null,null);


        Appointment returnedAppointment = appointmentRepositoryImpl.add(appointment);

        assertNotNull(returnedAppointment);
        /*assertEquals(secretary,returnedAdmin.getFullName());
        assertEquals(secretary,returnedAdmin.getPassword());*/
    }

}