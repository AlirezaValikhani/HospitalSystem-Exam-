package service;

import entity.Clinic;
import entity.Doctor;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.ClinicRepositoryImpl;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClinicService extends GenericService<Clinic,Integer> {
    private ClinicRepositoryImpl clinicRepositoryImpl = new ClinicRepositoryImpl();
    private Scanner scanner = new Scanner(System.in);
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private static DoctorService doctorService = new DoctorService();



    public String addClinic() {
        while (true) {
            System.out.println("Clinic name : ");
            try {
                String clinicName = scanner.nextLine();
                Clinic clinic = new Clinic(clinicName, new HashSet<>());
                add(clinic);
                return clinicName;
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showClinic() {
        List<Clinic> clinics = clinicRepositoryImpl.findAll();
        for (Clinic c : clinics) {
            System.out.println(c.toString());
        }
    }


    public void showClinicAndDoctors() {
        showClinic();
        Integer clinicId = enterClinicIdAndCheckExistence();
        Clinic clinic = findById(Clinic.class,clinicId);
        if (clinic != null) {
            List<Doctor> doctors = doctorService.findByClinicId(clinicId);
            for (Doctor d:doctors) {
                System.out.println(d.toString());
            }
        }
    }

    public Clinic findByName(String name) {
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

    /*public String enterClinicNameAndCheckExistence() {
        while (true) {
            System.out.println("Clinic name : ");
            try {
                String clinicName = scanner.nextLine();
                clinicNameChecker(clinicName);
                if (findByName(clinicName) == null) {
                    System.out.println("This name doesn't exist!!!");
                    return null;
                } else return clinicName;
            } catch (InvalidClinicName e) {
                System.out.println(e.getMessage());
            }
        }
    }*/


    public Integer enterClinicIdAndCheckExistence() {
        while (true) {
            System.out.println("Clinic id : ");
            try {
                Integer clinicId = scanner.nextInt();
                clinicIdChecker(clinicId);
                Clinic clinic = findById(Clinic.class,clinicId);
                if (clinic != null) {
                    return clinicId;
                } else System.out.println("This id doesn't exist!!!");
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

   /* public void clinicNameChecker(String nationalId) {
        if (nationalId.equals(""))
            throw new InvalidNationalCode("You can't enter space!");
        for (Character ch : nationalId.toCharArray()) {
            if (Character.isDigit(ch))
                throw new InvalidNationalCode("Clinic name should be just alpha!");
        }
    }*/

    public void clinicIdChecker(Integer id) {
        if (id == null)
            throw new InputMismatchException("You must enter something");
        for (Character ch : String.valueOf(id).toCharArray()) {
            if (!Character.isDigit(ch))
                throw new InputMismatchException("Clinic id should be just number!");
        }
    }
}



