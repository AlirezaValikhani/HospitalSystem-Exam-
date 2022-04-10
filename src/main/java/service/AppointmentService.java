package service;

import entity.*;
import entity.baseEntity.User;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.AppointmentRepositoryImpl;

import java.sql.Timestamp;
import java.util.Scanner;

public class AppointmentService extends GenericService<Appointment,Integer> {
    private String nationalCode;
    private AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private Utility utility = new Utility();
    private PatientService patientService = new PatientService();
    private DoctorService doctorService = new DoctorService();
    private SecretaryService secretaryService = new SecretaryService();

    public Appointment scheduling(User user) {
        Doctor doctor;
        Patient returnedPatient;
        Secretary returnedSecretary;
        Timestamp returnedDate;
        while (true) {
            nationalCode = utility.enterNationalCode();
            returnedPatient = patientService.findByNationalCode(nationalCode);
            if (returnedPatient == null)
                System.out.println("This national code doesn't exist!");
            else break;
        }
        while (true){
            doctorService.showDoctors();
            System.out.println("Enter doctor id : ");
            Integer id = scanner.nextInt();
            doctor = doctorService.findById(Doctor.class,id);
            if(doctor == null) System.out.println("Wrong id!!!");
            else break;
        }
        while (true){
            returnedDate = utility.returnTime();
            if(findByTime(returnedDate) != null)
                System.out.println("This time has already been booked!!!");
            else break;
        }
        while (true) {
            returnedSecretary = secretaryService.findByNationalCode(user.getNationalCode());
            if (returnedPatient == null)
                System.out.println("This national code doesn't exist!");
            else break;
        }
        Appointment appointment = new Appointment(returnedDate,true,returnedPatient,doctor,returnedSecretary);
        add(appointment);
        return appointment;
    }


    public Appointment findByTime(Timestamp timestamp){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Appointment appointment = appointmentRepository.findByTime(timestamp);
                transaction.commit();
                return appointment;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
