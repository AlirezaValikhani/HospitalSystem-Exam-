package repository.impl;

import entity.Patient;
import entity.Prescription;
import org.hibernate.SessionFactory;
import repository.PatientRepository;
import repository.SessionFactorySingleton;

import java.util.List;

public class PatientRepositoryImpl extends GenericRepositoryImpl<Patient,Integer> implements PatientRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public PatientRepositoryImpl() {
        super(Patient.class);
    }

    @Override
    public List<Patient> findAll() {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select p from Patient p", Patient.class)
                .list();
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            transaction.begin();
            session.createNativeQuery("TRUNCATE users CASCADE ", Patient.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public Patient findByNationalCode(String nationalCode) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select p from Patient p where p.nationalCode = :nationalCode", Patient.class)
                .setParameter("nationalCode",nationalCode)
                .getSingleResult();
    }
}
