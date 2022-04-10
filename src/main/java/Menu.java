import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import entity.Secretary;
import entity.baseEntity.User;
import service.*;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private boolean isTrue = true;
    private int input;
    private String username,password;
    private UserService userService = new UserService();
    private ClinicService clinicService = new ClinicService();
    private SecretaryService secretaryService = new SecretaryService();
    private DoctorService doctorService = new DoctorService();
    private PatientService patientService = new PatientService();
    private Utility utility = new Utility();
    private AppointmentService appointmentService = new AppointmentService();

    public int firstMenu(){
        System.out.println("   WELCOME");
        System.out.println("1-Sign In");
        System.out.println("2-Sign Up");
        System.out.println("3-Exit");
        input = utility.enterNumber();
        switch(input){
            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            default:
                return 0;
        }
    }

    public void signIn() {
        System.out.print("Username : ");
        username = scanner.nextLine();
        System.out.print("Password : ");
        password = scanner.nextLine();
        if( username.equals("admin") && password.equals("admin") )
            adminMenu();
        User user = userService.findByUserName(username);
        if(user == null){
            System.out.println("Wrong user name!");
            return;
        }
        if(!password.equals(user.getPassword())){
            System.out.println("Wrong password!");
            return;
        }
        if(String.valueOf(user.getUserType()).equals("SECRETORY")){
            System.out.println();
            secretoryMenu(user);
        }
        else if(String.valueOf(user.getUserType()).equals("PATIENT")){
            System.out.println();
            patientMenu(user);
        }
        else if(String.valueOf(user.getUserType()).equals("DOCTOR")){
            System.out.println();
            /*doctorMenu();*/
        }
        else
            System.out.println("It's very Strange!");
    }

    //Admin menu
    public void adminMenu() {
        isTrue = true;
        while(isTrue) {
            System.out.println("  Admin Menu");
            System.out.println("1-Add clinic");
            System.out.println("2-Add secretory");
            System.out.println("3-add doctor");
            System.out.println("4-Exit");
            input = utility.enterNumber();
            switch (input)
            {
                case 1:
                    if( clinicService.addClinic() != null )
                        System.out.println("Clinic added successfully!");
                    else
                        System.out.println("This name already exists!");
                    break;
                case 2:
                    Secretary result = secretaryService.addSecretory();
                    if(result == null )
                        System.out.println("Something is wrong!");
                    else
                        System.out.println();
                    break;

                case 3:
                    Doctor doctor = doctorService.addDoctor();
                    System.out.println(doctor.getFullName() + " added successfully");
                    break;
                case 4:
                    System.out.println("Good luck!");
                    isTrue = false;
                    break;
                default:
                    System.out.println("Wrong number!");
            }
        }
    }


    public void RegisterMenu() {
        System.out.println("Choose your position");
        System.out.println("1-Patient");
        System.out.println("2-Cancel");
        input = utility.enterNumber();
        switch(input) {
            case 1:
                Patient patient = patientService.patientRegister();
                if(patient == null )
                    System.out.println("Something is wrong!");
                else
                    System.out.println();
                break;
            case 2:
                System.out.println("Good luck!");
                return;
            default:
                System.out.println("You enter a wrong number!");
        }
    }


    public void patientMenu(User user) {
        System.out.println("1-Show all previous prescription");
        System.out.println("2-Show your information");
        System.out.println("3-Show clinics and doctors");
        System.out.println("4-Exit");
        System.out.print("Please select a number:");
        input = utility.enterNumber();
        switch(input) {
            case 1:
                int result = patientService.showPreviousPrescriptions(user);
                if(result == 0)
                    System.out.println("You don't hava any prescription!!!");
                return;
            case 2:
                String nationalCode = user.getNationalCode();
                int i = patientService.showPatientInformation(nationalCode);
                if(i == 0)
                    System.out.println("Something is wrong");
                return;
            case 3:
                clinicService.showClinicAndDoctors();
                return;
            case 4:
                System.out.println("Good luck!");
                return;
            default:
                System.out.println("You enter a wrong number!");
        }
    }


    public void secretoryMenu(User user) {
        System.out.println("Welcome to secretary section");
        System.out.println("1-Show doctor information");
        System.out.println("2-Show patients information");
        System.out.println("3-Scheduling system");
        System.out.println("4-Exit");
        input = utility.enterNumber();
        switch(input) {
            case 1:
                String nationalCode = utility.enterNationalCode();
                Doctor doctor = doctorService.findByNationalCode(nationalCode);
                if(doctor == null)
                    System.out.println("Something is wrong!!!");
                else System.out.println(doctor.toString());
                return;
            case 2:
                int i = patientService.showPatientInformation(null);
                if(i == 0)
                    System.out.println("Something is wrong");
                return;
            case 3:
                Appointment appointment = appointmentService.scheduling(user);
                if(appointment == null) System.out.println("Something is wrong!!!");
                else System.out.println("Meeting time recorded successfully. Yor appointment time : " + appointment.getTimestamp());
                return;
            case 4:
                System.out.println("Good luck!");
                return;
            default:
                System.out.println("You enter a wrong number!");
        }
    }
}
