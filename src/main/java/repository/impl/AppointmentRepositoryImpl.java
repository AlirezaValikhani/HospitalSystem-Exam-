package repository.impl;

import entity.Appointment;
import entity.Secretary;
import org.hibernate.SessionFactory;
import repository.AppointmentRepository;
import repository.SessionFactorySingleton;

import java.sql.Timestamp;
import java.util.List;

public class AppointmentRepositoryImpl extends GenericRepositoryImpl<Appointment,Integer> implements AppointmentRepository {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public AppointmentRepositoryImpl() {
        super(Appointment.class);
    }

    @Override
    public List<Appointment> findAll() {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select a from Appointment a", Appointment.class)
                .list();
    }

    @Override
    public void truncate() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.getTransaction();
            transaction.begin();
            session.createNativeQuery("TRUNCATE appointment CASCADE ", Appointment.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public Appointment findByTime(Timestamp timestamp) {
        var session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select a from Appointment a where a.timestamp = :timeStamp", Appointment.class)
                .setParameter("timeStamp",timestamp)
                .getSingleResult();
    }
}
