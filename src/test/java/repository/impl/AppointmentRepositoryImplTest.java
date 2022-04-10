package repository.impl;

import entity.*;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.SessionFactorySingleton;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentRepositoryImplTest {
    private static SessionFactory sessionFactory;
    private static AppointmentRepositoryImpl appointmentRepository;
    private static SecretaryRepositoryImpl secretaryRepository;
    private static PatientRepositoryImpl patientRepository;
    private static DoctorRepositoryImpl doctorRepository;
    private static PrescriptionRepositoryImpl prescriptionRepository;
    private static ClinicRepositoryImpl clinicRepository;
    Appointment appointment = new Appointment(new Timestamp(2020-10-10),true,null,null,null);
    Patient patient = new Patient("a","2","a",UserType.PATIENT,"d",null,new HashSet<>(),appointment,new HashSet<>(),null);
    Doctor doctor = new Doctor("a","3","w",UserType.DOCTOR,"s",null,new HashSet<>(),new HashSet<>(),new HashSet<>(),null);
    Secretary secretary = new Secretary("a","4","f",UserType.SECRETORY,new HashSet<>(),new HashSet<>());
    Prescription prescription = new Prescription(new Date(2020-10-1),"d","f",null,null);
    Clinic clinic = new Clinic("s",new HashSet<>());
    Appointment appointment1 = new Appointment(new Timestamp(2020-8-8),true,null,null,null);
    Patient patient1 = new Patient("e","9","e",UserType.PATIENT,"e",null,new HashSet<>(),appointment,new HashSet<>(),null);
    Doctor doctor1 = new Doctor("g","8","g",UserType.DOCTOR,"g",null,new HashSet<>(),new HashSet<>(),new HashSet<>(),null);
    Secretary secretary1 = new Secretary("h","7","h",UserType.SECRETORY,new HashSet<>(),new HashSet<>());
    Prescription prescription1 = new Prescription(new Date(2020-10-1),"d","f",null,null);
    Clinic clinic1 = new Clinic("f",new HashSet<>());

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        appointmentRepository = new AppointmentRepositoryImpl();
        secretaryRepository = new SecretaryRepositoryImpl();
        patientRepository = new PatientRepositoryImpl();
        doctorRepository = new DoctorRepositoryImpl();
        prescriptionRepository = new PrescriptionRepositoryImpl();
        clinicRepository = new ClinicRepositoryImpl();
    }

    @BeforeEach
    void truncate(){
        appointmentRepository.truncate();
        secretaryRepository.truncate();
        patientRepository.truncate();
        doctorRepository.truncate();
        prescriptionRepository.truncate();
        clinicRepository.truncate();
    }

    @Test
    void addTest(){
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        patient.setPrescription(prescription);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setSecretary(secretary);

        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.add(doctor);
                prescriptionRepository.add(prescription);
                patientRepository.add(patient);
                secretaryRepository.add(secretary);
                clinicRepository.add(clinic);
                appointmentRepository.add(appointment);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(appointment);
        assertNotNull(appointment.getDoctor());
        assertNotNull(appointment.getPatient());
    }


    @Test
    void updateTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        patient.setPrescription(prescription);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setSecretary(secretary);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.add(doctor);
                prescriptionRepository.add(prescription);
                patientRepository.add(patient);
                secretaryRepository.add(secretary);
                clinicRepository.add(clinic);
                appointmentRepository.add(appointment);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        appointment.setIsReserve(false);
        appointment.setTimestamp(new Timestamp(2020-8-8));
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointmentRepository.update(appointment);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals(false,appointment.getIsReserve());
        assertEquals(new Date(2020-8-8),appointment.getTimestamp());
    }


    @Test
    void deleteTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        patient.setPrescription(prescription);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setSecretary(secretary);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.add(doctor);
                prescriptionRepository.add(prescription);
                patientRepository.add(patient);
                secretaryRepository.add(secretary);
                clinicRepository.add(clinic);
                appointmentRepository.add(appointment);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Appointment returnedAppointment = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.delete(doctor);
                prescriptionRepository.delete(prescription);
                patientRepository.delete(patient);
                secretaryRepository.delete(secretary);
                clinicRepository.delete(clinic);
                appointmentRepository.delete(appointment);
                returnedAppointment = appointmentRepository.findById(Appointment.class,appointment.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNull(returnedAppointment);
    }

    @Test
    void findByIdTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        patient.setPrescription(prescription);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setSecretary(secretary);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.add(doctor);
                prescriptionRepository.add(prescription);
                patientRepository.add(patient);
                secretaryRepository.add(secretary);
                clinicRepository.add(clinic);
                appointmentRepository.add(appointment);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Appointment returnedAppointment = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                returnedAppointment = appointmentRepository.findById(Appointment.class,appointment.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(returnedAppointment.getIsReserve());
        assertNotNull(returnedAppointment);
    }


    @Test
    void findAllTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        patient.setPrescription(prescription);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setSecretary(secretary);
        doctor1.setClinic(clinic1);
        doctor1.setSecretary(secretary1);
        prescription1.setDoctor(doctor1);
        prescription1.setPatient(patient1);
        patient1.setPrescription(prescription1);
        appointment1.setPatient(patient1);
        appointment1.setDoctor(doctor1);
        appointment1.setSecretary(secretary1);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                doctorRepository.add(doctor);
                prescriptionRepository.add(prescription);
                patientRepository.add(patient);
                secretaryRepository.add(secretary);
                clinicRepository.add(clinic);
                appointmentRepository.add(appointment);
                doctorRepository.add(doctor1);
                prescriptionRepository.add(prescription1);
                patientRepository.add(patient1);
                secretaryRepository.add(secretary1);
                clinicRepository.add(clinic1);
                appointmentRepository.add(appointment1);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        List<Appointment> appointments = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointments = appointmentRepository.findAll();
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals(2,appointments.size());
        assertNotNull(appointments);
    }
}