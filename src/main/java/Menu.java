import entity.Secretary;
import entity.baseEntity.User;
import service.ClinicService;
import service.DoctorService;
import service.SecretaryService;
import service.UserService;

import java.sql.SQLException;
import java.util.InputMismatchException;
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

    public int firstMenu(){
        System.out.println("   WELCOME");
        System.out.println("1-Sign In");
        System.out.println("2-Sign Up");
        System.out.println("3-Exit");
        System.out.print("Please select a number:");
        while(true) {
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                System.out.print("Enter a number:");
            }
        }
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

    public void signIn() throws SQLException, ClassNotFoundException {
        System.out.print("Username : ");
        username = scanner.nextLine();
        System.out.print("Password : ");
        password = scanner.nextLine();
        if( username.equals("admin") && password.equals("admin") )
            adminMenu();
        User user = userService.findByUserName(username);
        if(user == null){
            System.out.println("User name or password is wrong!");
            return;
        }
        if(!password.equals(user.getPassword())){
            System.out.println("Wrong password!");
            return;
        }
        if(String.valueOf(user.getUserType()).equals("SECRETORY"))
            System.out.println();
            /*secretoryMenu(user.getId());*/
        else if(String.valueOf(user.getUserType()).equals("PATIENT"))
            System.out.println();
            /*customerMenu(user.getId());*/
        else if(String.valueOf(user.getUserType()).equals("DOCTOR"))
            System.out.println();
            /*doctorMenu();*/
        else
            System.out.println("It's very Strange!");
    }

    //Admin menu
    public void adminMenu() throws SQLException {
        isTrue = true;
        while(isTrue) {
            System.out.println("  Admin Menu");
            System.out.println("1-Add clinic");
            System.out.println("2-Add secretory");
            System.out.println("3-add doctor");
            System.out.println("4-Exit.");
            while(true) {
                try {
                    input = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException exception) {
                    scanner.nextLine();
                    System.out.print("Enter a number:");
                }
            }
            switch (input)
            {
                case 1:
                    if( clinicService.addClinic() == 1 )
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
                    doctorService.addDoctor();
                case 4:
                    System.out.println("Good luck!");
                    isTrue = false;
                    break;

                default:
                    System.out.println("Wrong number!");
            }
        }
    }
}
