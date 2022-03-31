package repository.impl;

import entity.Clinic;
import org.hibernate.SessionFactory;
import repository.ClinicRepository;
import repository.SessionFactorySingleton;

public class ClinicRepositoryImpl extends GenericRepositoryImpl<Clinic,Integer> implements ClinicRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public ClinicRepositoryImpl() {
        super(Clinic.class);
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
