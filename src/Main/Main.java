package Main;
import Accounts.*;   //importing package Accounts
import java.util.Scanner;

public class Main {

    //main method
    public static void main(String[] args){
        //calling methods
        loginRegisterText();
        functionsText();
    }

    //prompting the customer to log in or register an account
    private static void loginRegisterText(){

        while(true) {

            //printing text
            System.out.println("\n*************************************************");
            System.out.println("\t\t\t Banking Application");
            System.out.println("*************************************************");

            System.out.print("\t\t1. Login \t\t\t 2. Register\n\n"+
                    "Enter your choice: ");

            int choice = getChoice();  //getting customer choice

            if(choice == 1){

                //prompting the customer to log in if choice is '1'
                if(new Login().getLogin()){
                    break;  //breaking method once user is logged in
                }
            }
            else if(choice == 2){

                //prompting the customer to register an account if choice is '2'
                new Register().getRegister();
            }
            else{
                System.out.println("##Invalid choice\n");
            }
        }

    }


    //prompting functions to the customer once he/she is logged in
    private static void functionsText(){
        Functions functions = new Functions();  //object of Function class

        while(true) {

            System.out.print("\n1. Add Funds\t\t 2. Withdraw Funds\t\t 3. Send/Transfer Funds\n"+
                    "4. Request Money\t 5. Delete Account\t\t 6. Sign Out\n"+"Enter your choice: ");

            switch (getChoice()) {  //getting user input
                case 1 -> functions.getAddFunds();
                case 2 -> functions.getWithdrawFunds();
                case 3 -> functions.getTransferFunds();
                case 4 -> functions.getRequestMoney();
                case 5 -> functions.getDeleteAccount();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }


    //getting user input
    private static int getChoice(){
        Scanner choice = new Scanner(System.in);  //scanner to get input

        int customerChoice = 0;  //declaring variable

        //throwing exception if input is not integer
        try{
            customerChoice = choice.nextInt();
        }catch(Exception e){
            System.out.println("Please enter integer only.");
        }

        return customerChoice;  //returning input
    }

}
