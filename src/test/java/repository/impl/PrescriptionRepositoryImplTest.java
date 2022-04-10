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

class PrescriptionRepositoryImplTest {
    private static SessionFactory sessionFactory;
    private static PrescriptionRepositoryImpl prescriptionRepository;
    private static PatientRepositoryImpl patientRepository;
    private static AppointmentRepositoryImpl appointmentRepository;
    private static DoctorRepositoryImpl doctorRepository;
    private static SecretaryRepositoryImpl secretaryRepository;
    private static ClinicRepositoryImpl clinicRepository;
    Prescription prescription = new Prescription(new Timestamp(2020-10-10),"a","a",null,null);
    Patient patient = new Patient("a","1","a", UserType.PATIENT,"a",null,new HashSet<>(),null,new HashSet<>(),null);
    Appointment appointment = new Appointment(new Timestamp(2020-12-12),true,null,null,null);
    Secretary secretary = new Secretary("a","3","a",UserType.SECRETORY,new HashSet<>(),new HashSet<>());
    Doctor doctor = new Doctor("a","2","a",UserType.DOCTOR,"a",null,new HashSet<>(),new HashSet<>(),new HashSet<>(),null);
    Clinic clinic = new Clinic("a",new HashSet<>());
    Prescription prescription1 = new Prescription(new Date(2020-10-10),"a","a",null,null);
    Patient patient1 = new Patient("a","9","a", UserType.PATIENT,"a",null,new HashSet<>(),null,new HashSet<>(),null);
    Appointment appointment1 = new Appointment(new Timestamp(2020-12-12),true,null,null,null);
    Secretary secretary1 = new Secretary("a","8","a",UserType.SECRETORY,new HashSet<>(),new HashSet<>());
    Doctor doctor1 = new Doctor("a","7","a",UserType.DOCTOR,"a",null,new HashSet<>(),new HashSet<>(),new HashSet<>(),null);
    Clinic clinic1 = new Clinic("b",new HashSet<>());

    @BeforeAll
    static void initialize(){
        sessionFactory = SessionFactorySingleton.getInstance();
        prescriptionRepository = new PrescriptionRepositoryImpl();
        patientRepository = new PatientRepositoryImpl();
        appointmentRepository = new AppointmentRepositoryImpl();
        doctorRepository = new DoctorRepositoryImpl();
        secretaryRepository = new SecretaryRepositoryImpl();
        clinicRepository = new ClinicRepositoryImpl();
    }

    @BeforeEach
    void truncate(){
        prescriptionRepository.truncate();
        patientRepository.truncate();
        appointmentRepository.truncate();
        doctorRepository.truncate();
        secretaryRepository.truncate();
        clinicRepository.truncate();
    }

    @Test
    void addTest(){
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        appointment.setSecretary(secretary);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.setPrescription(prescription);
        patient.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);

        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointmentRepository.add(appointment);
                secretaryRepository.add(secretary);
                doctorRepository.add(doctor);
                clinicRepository.add(clinic);
                patientRepository.add(patient);
                prescriptionRepository.add(prescription);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(prescription);
        assertNotNull(prescription.getMedication());
    }


    @Test
    void updateTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        appointment.setSecretary(secretary);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.setPrescription(prescription);
        patient.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointmentRepository.add(appointment);
                secretaryRepository.add(secretary);
                doctorRepository.add(doctor);
                clinicRepository.add(clinic);
                patientRepository.add(patient);
                prescriptionRepository.add(prescription);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        prescription.setMedication("b");
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                prescriptionRepository.update(prescription);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals("b",prescription.getMedication());
    }


    @Test
    void deleteTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        appointment.setSecretary(secretary);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.setPrescription(prescription);
        patient.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointmentRepository.add(appointment);
                secretaryRepository.add(secretary);
                doctorRepository.add(doctor);
                clinicRepository.add(clinic);
                patientRepository.add(patient);
                prescriptionRepository.add(prescription);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Prescription returnedPrescription = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointmentRepository.delete(appointment);
                secretaryRepository.delete(secretary);
                doctorRepository.delete(doctor);
                clinicRepository.delete(clinic);
                patientRepository.delete(patient);
                prescriptionRepository.delete(prescription);
                returnedPrescription = prescriptionRepository.findById(Prescription.class,prescription.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNull(returnedPrescription);
    }

    @Test
    void findByIdTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        appointment.setSecretary(secretary);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.setPrescription(prescription);
        patient.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointmentRepository.add(appointment);
                secretaryRepository.add(secretary);
                doctorRepository.add(doctor);
                clinicRepository.add(clinic);
                patientRepository.add(patient);
                prescriptionRepository.add(prescription);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        Prescription returnedPrescription = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                returnedPrescription = prescriptionRepository.findById(Prescription.class,prescription.getId());
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertNotNull(returnedPrescription.getMedication());
        assertNotNull(returnedPrescription);
    }


    @Test
    void findAllTest() {
        doctor.setClinic(clinic);
        doctor.setSecretary(secretary);
        appointment.setSecretary(secretary);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.setPrescription(prescription);
        patient.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        doctor1.setClinic(clinic1);
        doctor1.setSecretary(secretary1);
        appointment1.setSecretary(secretary1);
        appointment1.setDoctor(doctor1);
        appointment1.setPatient(patient1);
        patient1.setPrescription(prescription1);
        patient1.setAppointment(appointment1);
        prescription1.setDoctor(doctor1);
        prescription1.setPatient(patient1);
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                appointmentRepository.add(appointment);
                secretaryRepository.add(secretary);
                doctorRepository.add(doctor);
                clinicRepository.add(clinic);
                patientRepository.add(patient);
                prescriptionRepository.add(prescription);
                appointmentRepository.add(appointment1);
                secretaryRepository.add(secretary1);
                doctorRepository.add(doctor1);
                clinicRepository.add(clinic1);
                patientRepository.add(patient1);
                prescriptionRepository.add(prescription1);
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        List<Prescription> prescriptions = null;
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                prescriptions = prescriptionRepository.findAll();
                transaction.commit();
            } catch (Exception e) {
                fail();
            }
        }

        assertEquals(2, prescriptions.size());
        assertNotNull(prescriptions);
    }
}