package Accounts;
import SQL.RegisterDatabase;  //import class RegisterDatabase from SQL package
import java.util.Scanner;

public class Register {

    Scanner scanner = new Scanner(System.in);  //scanner to get input

    //passing the provided inputs to RegisterDatabase class to store the customers data in the sql server
    public void getRegister(){
        new RegisterDatabase().registerCustomer(getFirstname(),getLastname(),getEmail(),getPassword(),getAmount());
    }

    //getting firstname
    private String getFirstname(){
        System.out.println("\n***Enter The Following Details To Create An Account Successfully***");
        System.out.print("""
                1. Email must have '@' and '.' character
                2. Password must be more than 12 characters
                3. Fund amount has to be more than 500
                """);
        System.out.print("Firstname: ");
        return scanner.nextLine();
    }

    //getting lastname
    private String getLastname(){
        System.out.print("Lastname: ");
        return scanner.nextLine();
    }

    //getting email
    private String getEmail(){

        String email;  //declaring variable

        while(true) {
            System.out.print("Email: ");

            email = scanner.nextLine();  //scanner to get input

            //checking whether the inputted email include '@' and '.' or not
            if(email.contains("@") && email.contains(".")){
                break;
            }
            else{
                System.out.println("##Email must include '@' and '.'");
            }
        }

        return email;  //returning variable
    }

    //getting password
    private String getPassword(){
        String password;  //declaring variable

        while(true) {
            System.out.print("Password: ");

            password = scanner.nextLine();  //scanner to get input

            //checking whether inputted value is more than 12 chars or not.
            if(password.length() > 12){
                break;
            }
            else{
                System.out.println("##Password has to be more than 12 characters long");
            }
        }

        return password;  //returning variable
    }

    //getting funds/amount
    private double getAmount(){
        double amount = 0;  //declaring variable

        while(true) {
            System.out.print("Add Amount: ");

            //throwing exception if input is not valid
            try {
                amount = scanner.nextDouble();

                //checking whether the inputted amount is more than 500 or not
                if (amount > 500) {
                    break;
                } else {
                    System.out.println("##Amount has to be more than $500");
                }

            }catch(Exception e){  //exception
                System.out.println("##Invalid amount");
            }
        }

        return amount;  //returning variable
    }

}
