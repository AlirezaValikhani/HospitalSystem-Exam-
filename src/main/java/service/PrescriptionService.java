package service;

import entity.Prescription;
import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.PrescriptionRepositoryImpl;

import java.util.List;

public class PrescriptionService extends GenericService<Prescription,Integer>{
    private PrescriptionRepositoryImpl prescriptionRepository = new PrescriptionRepositoryImpl();
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public List<Prescription> findPrescriptionByUserid(Integer id){
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return prescriptionRepository.findPrescriptionByUserId(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
    }


    public List<Prescription> showPreviousPrescriptions(User user){
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return prescriptionRepository.findPrescriptionByUserId(user.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
    }
}


