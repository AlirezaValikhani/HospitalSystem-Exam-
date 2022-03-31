package service;

import entity.Clinic;
import entity.Doctor;
import entity.Secretary;
import entity.UserType;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.DoctorRepositoryImpl;

import java.util.HashSet;
import java.util.Scanner;

public class DoctorService extends GenericService<Doctor,Integer>{
    private final Scanner scanner = new Scanner(System.in);
    private DoctorRepositoryImpl doctorRepositoryImpl = new DoctorRepositoryImpl();
    private Utility utility = new Utility();
    private String fullName,nationalCode,password,expertise;
    private ClinicService clinicService = new ClinicService();
    private SecretaryService secretaryService = new SecretaryService();
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public void addDoctor(){
        fullName = utility.enterFullName();
        nationalCode = utility.enterNationalCode();
        if(findByNationalCode(nationalCode) != null)
            System.out.println("This national code already exists!");
        password = utility.enterPassword();
        System.out.println("Expertise : ");
        expertise = scanner.nextLine();
        clinicService.showClinic();
        System.out.println("Clinic name : ");
        String clinicName = scanner.nextLine();
        if(clinicService.findByName(clinicName) != null)
            System.out.println("This name already exists");
        Clinic clinic = new Clinic(clinicName,new HashSet<>());
        Secretary secretary = secretaryService.addSecretory();
        Doctor doctor = new Doctor(fullName,nationalCode,password, UserType.DOCTOR,expertise,clinic,new HashSet<>(),new HashSet<>(),new HashSet<>(),secretary);
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
}
