package repository.impl;

import entity.Patient;
import entity.Prescription;
import org.hibernate.SessionFactory;
import repository.PatientRepository;
import repository.PrescriptionRepository;
import repository.SessionFactorySingleton;

public class PrescriptionRepositoryImpl extends GenericRepositoryImpl<Prescription,Integer> implements PrescriptionRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public PrescriptionRepositoryImpl() {
        super(Prescription.class);
    }
}
