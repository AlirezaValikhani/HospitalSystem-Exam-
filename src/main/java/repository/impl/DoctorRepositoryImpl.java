package repository.impl;

import entity.Clinic;
import entity.Doctor;
import org.hibernate.SessionFactory;
import repository.ClinicRepository;
import repository.DoctorRepository;
import repository.SessionFactorySingleton;

public class DoctorRepositoryImpl extends GenericRepositoryImpl<Doctor,Integer> implements DoctorRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public DoctorRepositoryImpl() {
        super(Doctor.class);
    }


    @Override
    public Doctor findByNationalCode(String nationalCode) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select d from Doctor d where d.nationalCode = :nationalCode",Doctor.class)
                .setParameter("national_code",nationalCode)
                .getSingleResult();
    }
}
