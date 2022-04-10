package service;

import entity.Patient;
import entity.Prescription;
import entity.Secretary;
import entity.UserType;
import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import org.hibernate.sql.HSQLCaseFragment;
import repository.SessionFactorySingleton;
import repository.impl.PatientRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class PatientService extends GenericService<Patient,Integer>{
    private Scanner scanner = new Scanner(System.in);
    private String fullName,password,nationalCode,diseaseRecords;
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
    private PrescriptionService prescriptionService = new PrescriptionService();
    private Utility utility = new Utility();

    public Patient patientRegister(){
        fullName = utility.enterFullName();
        nationalCode = utility.enterNationalCode();
        if(findByNationalCode(nationalCode) != null)
            System.out.println("This national code already exists!");
        password = utility.enterPassword();
        System.out.println("Disease records : " );
        diseaseRecords = scanner.nextLine();
        Patient patient = new Patient(fullName, nationalCode, password, UserType.PATIENT,diseaseRecords);
        Patient returnedPatient = add(patient);
        if (returnedPatient == null) {
            System.out.println("Error");
            return null;
        } else {
            System.out.println(returnedPatient.getFullName() + " Successfully added");
            return returnedPatient;
        }
    }


    public Patient findByNationalCode(String nationalCode){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Patient patient = patientRepository.findByNationalCode(nationalCode);
                transaction.commit();
                return patient;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public int showPatientInformation(String nationalCode){
        nationalCode = utility.enterNationalCode();
        Patient patient = findByNationalCode(nationalCode);
        if(patient !=null){
            System.out.println(patient.toString());
            return 1;
        }
        else return 0;
    }


    public int showPreviousPrescriptions(User user){
        List<Prescription> prescriptions = prescriptionService.showPreviousPrescriptions(user);
        if(prescriptions == null)
            return 0;
        else for (Prescription p:prescriptions) {
            System.out.println(p.toString());
            return 1;
        }
        return 0;
    }
}
