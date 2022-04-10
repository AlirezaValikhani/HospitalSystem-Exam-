package repository.impl;

import entity.Clinic;
import org.hibernate.SessionFactory;
import repository.ClinicRepository;
import repository.SessionFactorySingleton;
import java.util.List;

public class ClinicRepositoryImpl extends GenericRepositoryImpl<Clinic,Integer> implements ClinicRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public ClinicRepositoryImpl() {
        super(Clinic.class);
    }

    @Override
    public List<Clinic> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                List<Clinic> clinics = session
                        .createQuery("select c from Clinic c",Clinic.class)
                        .list();
                transaction.commit();
                return clinics;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            transaction.begin();
            session.createNativeQuery("TRUNCATE clinic CASCADE ", Clinic.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public Clinic findByName(String name) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select c from Clinic c where c.name = :name",Clinic.class)
                .setParameter("name",name)
                .getSingleResult();
    }
}
