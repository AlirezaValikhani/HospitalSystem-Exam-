package repository;


import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {
    private SessionFactorySingleton() {}

    private static class Holder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();


            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Secretary.class)
                    .addAnnotatedClass(Appointment.class)
                    .addAnnotatedClass(Clinic.class)
                    .addAnnotatedClass(Doctor.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Prescription.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return Holder.INSTANCE;
    }
}
