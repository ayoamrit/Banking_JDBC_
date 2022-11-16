package Accounts;
import java.util.Scanner;
import SQL.LoginDatabase;   //importing class LoginDatabase from SQL package

public class Login {
    static Scanner scanner = new Scanner(System.in);  //scanner to get input

    //getting customer username
    private String getUsername(){

        System.out.print("Username: ");
        return scanner.nextLine();
    }

    //getting customer password
    private String getPassword(){
        System.out.print("Password: ");
        return scanner.nextLine();
    }

    //passing username and password to FunctionsDatabase to check whether provided information is true or not
    public boolean getLogin(){
        return new LoginDatabase().loginCustomer(getUsername(),getPassword());
    }
}
