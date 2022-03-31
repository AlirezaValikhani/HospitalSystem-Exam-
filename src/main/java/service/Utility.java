package service;

import exception.InvalidName;
import exception.InvalidNationalCode;
import exception.InvalidPassword;

import java.util.Scanner;

public class Utility {
    private Scanner scanner = new Scanner(System.in);
    private InvalidName invalidName = new InvalidName();
    private String fullName,nationalCode,password;


    public String enterFullName(){
        while(true){
            System.out.print("Enter name(just alpha):");
            try {
                fullName = scanner.nextLine();
                checkName(fullName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return fullName;
    }

    public void checkName(String name){
        if(name.length() < 3 )
            throw new InvalidName("Name should be more than 2 character!");
        for (Character ch:name.toCharArray()) {
            if(Character.isDigit(ch))
                throw new InvalidName("Name can not have number!");
        }
        for (Character ch:name.toCharArray()) {
            if(!Character.isAlphabetic(ch))
                throw new InvalidName("name can't have Sign(!,@,#,%,...)");

        }
    }

    public String enterNationalCode(){
        while(true){
            System.out.print("National code : ");
            try {
                nationalCode = scanner.nextLine();
                nationalIdChecker(nationalCode);
                break;
            }catch (InvalidNationalCode except){
                System.out.println(except.getMessage());
            }
        }
        return nationalCode;
    }

    public void nationalIdChecker(String nationalId){
        if(nationalId.length() > 10 )
            throw new InvalidNationalCode("National code can't be more than 10 number!");
        if(nationalId.equals(""))
            throw new InvalidNationalCode("You can't enter space!");
        for (Character ch:nationalId.toCharArray()) {
            if(!Character.isDigit(ch))
                throw new InvalidNationalCode("National code should be just number!");
        }
    }

    public String enterPassword(){
        while(true) {
            System.out.print("Password:");
            try {
                password = scanner.nextLine();
                passwordCheck(password);
                break;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
        return password;
    }


    public void passwordCheck(String password) {
        char[] passwordArray = password.toCharArray();
        char[] signArray =  new char[] {'!','@','#','$','%','^','&','*','(',')','-','+','=','.',',','>','<','?','/','|',':',';'};
        int sign = 0;
        if (password.length() < 3)
            throw new InvalidPassword("password should be more than 2 ");
                for(int i=0;i<signArray.length;i++)
                    for(int j=0;j<passwordArray.length;j++)
                        if(signArray[i] == passwordArray[j])
                            ++sign;
                if(sign != 0){
                    System.out.println("You can't use signs!");
        }
    }
}
