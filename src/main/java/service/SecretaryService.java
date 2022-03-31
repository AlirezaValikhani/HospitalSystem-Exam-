package service;

import entity.Secretary;
import entity.UserType;
import repository.impl.SecretaryRepositoryImpl;

import javax.swing.text.Utilities;
import java.util.Scanner;

public class SecretaryService extends GenericService<Secretary,Integer>{
    private final Scanner scanner = new Scanner(System.in);
    private Utility utility = new Utility();
    private String fullName,nationalCode,password;
    private SecretaryRepositoryImpl secretaryRepositoryImpl = new SecretaryRepositoryImpl();

    public Secretary addSecretory() {
        fullName = utility.enterFullName();
        nationalCode = utility.enterNationalCode();
        if(secretaryRepositoryImpl.findByNationalCode(nationalCode) != null)
            System.out.println("This national code already exists!");
        password = utility.enterPassword();
        Secretary secretary = new Secretary(fullName, nationalCode, password, UserType.SECRETORY);
        Secretary returnedSecretory = add(secretary);
        if (returnedSecretory == null) {
            System.out.println("Error");
            return null;
        } else {
            System.out.println(returnedSecretory.getFullName() + " Successfully added");
            return returnedSecretory;
        }
    }
}
