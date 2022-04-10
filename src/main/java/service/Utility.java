package service;

import exception.InvalidClinicName;
import exception.InvalidName;
import exception.InvalidNationalCode;
import exception.InvalidPassword;

import java.sql.Timestamp;
import java.util.InputMismatchException;
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
                nationalCodeChecker(nationalCode);
                break;
            }catch (InvalidNationalCode except){
                System.out.println(except.getMessage());
            }
        }
        return nationalCode;
    }

    public void nationalCodeChecker(String nationalId){
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

    public Integer enterNumber(){
        int i;
        while (true){
            try {
                System.out.print("Enter number : ");
                i = scanner.nextInt();
                scanner.nextLine();
                return i;
            }catch (InputMismatchException exception){
                scanner.nextLine();
                System.out.println("You can just Enter number!");
            }
            scanner.nextLine();
        }
    }

    public String enterClinicName(){
        while (true){
            System.out.println("Clinic name : ");
            try{
                String clinicName = scanner.nextLine();
                clinicNameChecker(clinicName);
                return clinicName;
            }catch (InvalidClinicName e){
                System.out.println(e.getMessage());
            }
        }
    }


    /*public String enterClinicNameAndCheckExistence(){
        while (true){
            System.out.println("Clinic name : ");
            try{
                String clinicName = scanner.nextLine();
                clinicNameChecker(clinicName);
                if(clinicService.findByName(clinicName) == null){
                    System.out.println("This name doesn't exist!!!");
                    return null;
                }
                else return clinicName;
            }catch (InvalidClinicName e){
                System.out.println(e.getMessage());
            }
        }
    }*/


    public void clinicNameChecker(String nationalId){
        if(nationalId.equals(""))
            throw new InvalidNationalCode("You can't enter space!");
        for (Character ch:nationalId.toCharArray()) {
            if(Character.isDigit(ch))
                throw new InvalidNationalCode("Clinic name should be just alpha!");
        }
    }

    public Timestamp returnTime(){
        while (true) {
            System.out.println("Enter appointment date (like this -> 2022-10-02 18:48:00) : ");
            String text = scanner.nextLine();
            try {
                return Timestamp.valueOf(text);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

