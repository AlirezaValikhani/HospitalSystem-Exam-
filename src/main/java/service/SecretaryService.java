package service;

import entity.Clinic;
import entity.Secretary;
import entity.UserType;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.impl.SecretaryRepositoryImpl;

import javax.swing.text.Utilities;
import java.util.Scanner;

public class SecretaryService extends GenericService<Secretary,Integer>{
    private final Scanner scanner = new Scanner(System.in);
    private Utility utility = new Utility();
    private String fullName,nationalCode,password;
    private SecretaryRepositoryImpl secretaryRepositoryImpl = new SecretaryRepositoryImpl();
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Secretary addSecretory() {
        fullName = utility.enterFullName();
        nationalCode = utility.enterNationalCode();
        if(findByNationalCode(nationalCode) != null)
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


    public Secretary findByNationalCode(String nationalCode){
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                Secretary secretary = secretaryRepositoryImpl.findByNationalCode(nationalCode);
                transaction.commit();
                return secretary;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
