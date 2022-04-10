package service;

import entity.Clinic;
import entity.Doctor;
import entity.Secretary;
import entity.UserType;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.DoctorRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DoctorService extends GenericService<Doctor,Integer>{
    private final Scanner scanner = new Scanner(System.in);
    private DoctorRepositoryImpl doctorRepositoryImpl = new DoctorRepositoryImpl();
    private Utility utility = new Utility();
    private String fullName,nationalCode,password,expertise;
    private ClinicService clinicService = new ClinicService();
    private SecretaryService secretaryService = new SecretaryService();
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Doctor addDoctor() {
        Secretary secretary ;
        Clinic clinic;
        fullName = utility.enterFullName();
        while (true) {
            nationalCode = utility.enterNationalCode();
            Doctor returnedDoctor = findByNationalCode(nationalCode);
            if (returnedDoctor != null)
                System.out.println("This national code already exists!");
            else break;
        }
        password = utility.enterPassword();
        System.out.println("Expertise : ");
        expertise = scanner.nextLine();
        while (true) {
            clinicService.showClinic();
            Integer clinicId = clinicService.enterClinicIdAndCheckExistence();
            clinic = clinicService.findById(Clinic.class,clinicId);
            if (clinic == null) {
                System.out.println("This name doesn't exists!!!");
            }
            else break;
        }
        while (true) {
            secretaryService.showSecretaries();
            String secretaryNationalCode = utility.enterNationalCode();
            secretary = secretaryService.findByNationalCode(secretaryNationalCode);
            if (secretary == null)
                System.out.println("Enter correct national code!!!");
            else break;
        }
        Doctor doctor = new Doctor(fullName, nationalCode, password, UserType.DOCTOR, expertise, clinic, new HashSet<>(), new HashSet<>(), new HashSet<>(), secretary);
        add(doctor);
        return doctor;
    }


    public Doctor findByNationalCode(String nationalCode){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Doctor doctor = doctorRepositoryImpl.findByNationalCode(nationalCode);
                transaction.commit();
                return doctor;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public List<Doctor> findByClinicId(Integer clinicId){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                List<Doctor> doctors = doctorRepositoryImpl.findByClinicId(clinicId);
                transaction.commit();
                return doctors;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public List<Doctor> findAll(){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                List<Doctor> doctors = doctorRepositoryImpl.findAll();
                transaction.commit();
                return doctors;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public void showDoctors(){
        List<Doctor> doctors = doctorRepositoryImpl.findAll();
        for (Doctor d:doctors) {
            System.out.println(d.toString());
        }
    }
}
