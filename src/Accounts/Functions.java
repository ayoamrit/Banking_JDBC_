package Accounts;
import SQL.FunctionsDatabase;  //importing FunctionDatabase class from SQL package
import java.util.Scanner;

public class Functions {
    Scanner scanner = new Scanner(System.in);  //scanner to get input
    FunctionsDatabase functionsDB = new FunctionsDatabase();  //object of FunctionsDatabase class

    //checking whether the input is double or not
    private boolean checkDouble(String value){

        try{

            //converting string to double
            Double.parseDouble(value);

        }catch(NumberFormatException e){  //throwing exception and returning false
            return false;
        }

        return true;  //return true if input is double
    }

    //getting double
    private Double getDouble(String value){
        return Double.parseDouble(value);
    }


    //getting addFunds
    public void getAddFunds(){
        System.out.print("Enter Amount Of Funds You Would Like To Add: ");
        String addFunds = scanner.nextLine();  //scanner to get input

        //checking if the value is double and is more than 0
        if(checkDouble(addFunds) && getDouble(addFunds) > 0){

            //calling method from FunctionsDatabase class and passing variable 'addFunds'
            functionsDB.customerAddFunds(getDouble(addFunds));
        }
        else{
            System.out.println("##Invalid input");
        }

    }


    //getting withdrawFunds
    public void getWithdrawFunds(){
        System.out.print("Enter amount of funds you would like to withdraw: ");
        String withdrawFunds = scanner.nextLine();  //scanner to get input

        //checking if the value is double
        if(checkDouble(withdrawFunds)){

            //calling method from FunctionsDatabase class and passing variable 'withdrawFunds'
            functionsDB.customerWithdrawFunds(getDouble(withdrawFunds));
        }
        else{
            System.out.println("##Invalid input");
        }

    }

    //getting transferFunds
    public void getTransferFunds(){
        System.out.print("Enter the amount you would like to transfer: ");
        String transferAmount = scanner.nextLine();  //scanner to get input

        //check whether the value is double or not
        if(checkDouble(transferAmount)){
            System.out.print("Enter username of payee: ");
            String transferTo = scanner.nextLine();  //scanner to get input

            //calling method and passing variables 'transferAmount' and 'transferTo'
            functionsDB.customerTransferFunds(getDouble(transferAmount), transferTo);
        }
        else{
            System.out.println("##Invalid input");
        }
    }

    //getting requestMoney
    public void getRequestMoney(){
        System.out.print("Enter the username: ");

        String requestFrom = scanner.nextLine();

        System.out.print("Enter Amount: ");
        String requestAmount = scanner.nextLine();

        if(checkDouble(requestAmount)){
            functionsDB.customerRequestMoney(requestFrom,getDouble(requestAmount));
        }
    }

    //getting deleteAccount
    public void getDeleteAccount(){
        functionsDB.customerDeleteAccount();
    }
}
