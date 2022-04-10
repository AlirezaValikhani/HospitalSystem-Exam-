package repository.impl;

import entity.Patient;
import entity.Prescription;
import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import repository.PrescriptionRepository;
import repository.SessionFactorySingleton;

import java.util.List;

public class PrescriptionRepositoryImpl extends GenericRepositoryImpl<Prescription,Integer> implements PrescriptionRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public PrescriptionRepositoryImpl() {
        super(Prescription.class);
    }

    @Override
    public List<Prescription> findAll() {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select p from Prescription p", Prescription.class)
                .list();
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            transaction.begin();
            session.createNativeQuery("TRUNCATE prescription CASCADE ", Prescription.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public List<Prescription> findPrescriptionByUserId(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM Prescription as p WHERE p.patient.id = :patient_id ",
                Prescription.class);
        query.setParameter("patient_id", id);
        return query.list();
    }
}
