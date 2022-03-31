package repository.impl;

import entity.Doctor;
import entity.Patient;
import org.hibernate.SessionFactory;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.SessionFactorySingleton;

public class PatientRepositoryImpl extends GenericRepositoryImpl<Patient,Integer> implements PatientRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public PatientRepositoryImpl() {
        super(Patient.class);
    }
}
