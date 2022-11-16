package SQL;
import java.sql.ResultSet;

public class FunctionsDatabase extends Connect{  //Child class of Connect class

    //getting amount of funds that customer have in its account
    private double getAmount(String username){
        double amount = 0;  //declaring variable

        try{

            //executing query
            ResultSet resultSet = getStatement().executeQuery("select * from data where Email = '"+username+"'");

            while(resultSet.next()) {

                //getting the value from 'Amount' column
                amount = Double.parseDouble(resultSet.getString("Amount"));
            }
        } catch(Exception e){  //throwing exception
            System.out.println("#####Amount Error");
        }

        return amount;
    }

    //checking whether the customer account exists or not
    private boolean checkAccountExistence(String accountUsername){

        try{

            //returning true if account exists
            return getStatement().executeQuery("select * from data where email = '" + accountUsername + "'").next();
        }catch(Exception e){
            return false;
        }
    }

    //adding customer funds
    public void customerAddFunds(double fundAmount){

        try{

            //getting already existing customer funds and adding the amount of fund customer want
            //to add.
            double totalAmount = getAmount(getCustomerUsername()) + fundAmount;

            //executing query
            getStatement().executeUpdate("UPDATE data set Amount = "+(totalAmount)+" where Email = '" +getCustomerUsername()+ "'");

            System.out.println("***Funds added successfully");

        }catch(Exception e){  //throwing exception
            System.out.println("#####AddFunds Error");
        }
    }

    //withdrawing funds from customer's account
    public void customerWithdrawFunds(double withdrawAmount){
        try{

            //getting the amount of already existing funds in customer's account
            double totalAmount = getAmount(getCustomerUsername());

            //if the withdrawal amount is more than amount of already existing funds in account.
            if(withdrawAmount > totalAmount){

                System.out.println("##You have insufficient funds");
            }
            else {

                //executing query
                getStatement().executeUpdate("UPDATE data set Amount = " + (totalAmount - withdrawAmount) + " where Email = '" + getCustomerUsername() + "'");

                System.out.println("***Withdraw successful");
            }
        }catch(Exception e){    //throwing exception
            System.out.println("#####Withdraw Error");
        }
    }

    //transferring funds
    public void customerTransferFunds(double transferAmount, String payeeUsername){

        try{
            //getting available funds in the account the customer
            double totalAmount = getAmount(getCustomerUsername());

            //if transfer amount is more than available funds in the customer's account
            if(transferAmount > totalAmount){
                System.out.println("##Insufficient funds");
            }
            else{

                //if payee account exists
                if(checkAccountExistence(payeeUsername)){

                    //executing query, taking off the amount of funds from customer account
                    getStatement().executeUpdate("UPDATE data set Amount = " + (totalAmount - transferAmount) + " where Email = '" + getCustomerUsername() + "'");

                    double payeeFunds = getAmount(payeeUsername);  //getting available funds in payee account

                    //executing query, adding the amount of funds customer transferred in the payee account
                    getStatement().executeUpdate("UPDATE data set Amount = "+(payeeFunds+transferAmount)+" where Email = '"+payeeUsername+"'");

                    System.out.println("***Funds transferred successfully");
                }
                //if payee accounts does not exist
                else{
                    System.out.println("##'"+payeeUsername+"' account does not exist");
                }
            }

        }catch(Exception e){  //throwing exception
            System.out.println("##TransferFunds Error");
        }
    }

    //requesting money from other users
    public void customerRequestMoney(String requestFrom, Double requestAmount){
        try {

            //checking account existence
            if (checkAccountExistence(requestFrom)) {

                //calling 'checkNotification', to get fetch notifications from database if there's any,
                // method and creating a string of query
                String REQUEST = checkNotification(requestFrom)+getCustomerUsername()+" requested $"+ requestAmount+",";

                //executing query and sending a notification to other user from customer's account
                getStatement().executeUpdate("update data set Notification = '"+REQUEST+"' where Email = '"+requestFrom+"'");
                System.out.println("***Request sent");
            }
            else{  //if users account does not exist
                System.out.println("##'"+requestFrom+"' does not exist");
            }
        }catch(Exception e){  //throwing exception
            System.out.println("#####RequestMoney Error");
        }
    }

    //checking or fetching notifications from database
    public String checkNotification(String username){

        String fetchNotification = null;  //declaring variable
        try{

            //executing query to fetch record
            ResultSet resultSet = getStatement().executeQuery("select notification from data where Email = '"+username+"'");

            if(resultSet.next()) {  //fetching record
                fetchNotification = resultSet.getString("Notification");
            }
        }catch(Exception e){   //throwing exception
            System.out.println("#####Notification Error");
        }

        return fetchNotification;  //returning String variable
    }

    //deleting customer account
    public void customerDeleteAccount(){
        try{
            //executing query to delete customer account
            getStatement().executeUpdate("DELETE FROM data where Email = '"+getCustomerUsername()+"'");
            System.out.println("***Account Deleted Successfully");

        }catch(Exception e){  //throwing exception
            System.out.println("#####DeleteAccount Error");
        }
    }
}
