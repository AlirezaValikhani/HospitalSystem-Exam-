package repository.impl;

import entity.Appointment;
import org.hibernate.SessionFactory;
import repository.AppointmentRepository;
import repository.SessionFactorySingleton;

public class AppointmentRepositoryImpl extends GenericRepositoryImpl<Appointment,Integer> implements AppointmentRepository {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public AppointmentRepositoryImpl() {
        super(Appointment.class);
    }

}
