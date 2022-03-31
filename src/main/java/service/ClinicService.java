package service;

import entity.Clinic;
import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.ClinicRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ClinicService extends GenericService<Clinic,Integer>{
    private ClinicRepositoryImpl clinicRepositoryImpl = new ClinicRepositoryImpl();
    private Scanner scanner = new Scanner(System.in);
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Integer addClinic(){
        System.out.println("Clinic name : ");
        String clinicName = scanner.nextLine();
        Clinic clinic = new Clinic(clinicName,new HashSet<>());
        Clinic returnedClinic = add(clinic);
        if(returnedClinic != null)
        return 1;
        else return null;
    }

    public void showClinic(){
        List<Clinic> clinics = clinicRepositoryImpl.findAll();
        for (Clinic c: clinics) {
            System.out.println(c.toString());
        }
    }

    public Clinic findByName(String name){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Clinic clinic = clinicRepositoryImpl.findByName(name);
                transaction.commit();
                return clinic;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
