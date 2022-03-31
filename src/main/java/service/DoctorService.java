package service;

import entity.Clinic;
import entity.Doctor;
import entity.Secretary;
import entity.UserType;
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

    public void addDoctor(){
        fullName = utility.enterFullName();
        nationalCode = utility.enterNationalCode();
        if(doctorRepositoryImpl.findByNationalCode(nationalCode) != null)
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
}
