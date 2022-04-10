package repository.impl;

import entity.Clinic;
import entity.Doctor;
import entity.Secretary;
import org.hibernate.SessionFactory;
import repository.ClinicRepository;
import repository.DoctorRepository;
import repository.SessionFactorySingleton;

import java.util.List;

public class DoctorRepositoryImpl extends GenericRepositoryImpl<Doctor,Integer> implements DoctorRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public DoctorRepositoryImpl() {
        super(Doctor.class);
    }


    @Override
    public List<Doctor> findAll() {
        var session = sessionFactory.getCurrentSession();
        var transaction = session.getTransaction();
        transaction.begin();
        List<Doctor> doctors = session
                .createQuery("select d from Doctor d", Doctor.class)
                .list();
        transaction.commit();
        return doctors;
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            transaction.begin();
            session.createNativeQuery("TRUNCATE users CASCADE ", Doctor.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public Doctor findByNationalCode(String nationalCode) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select d from Doctor d where d.nationalCode = :nationalCode",Doctor.class)
                .setParameter("nationalCode",nationalCode)
                .getSingleResult();
    }

    @Override
    public List<Doctor> findByClinicId(Integer clinicId) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select d from Doctor d where d.clinic.id = :clinicId",Doctor.class)
                .setParameter("clinicId",clinicId)
                .list();
    }
}
