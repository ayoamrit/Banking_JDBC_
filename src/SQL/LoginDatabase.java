package SQL;
import java.sql.*;
import java.util.Objects;

public class LoginDatabase extends Connect{   //child class of Connect class
    public boolean loginCustomer(String customerUsername, String customerPassword){

        //defining variable
        boolean checkLogin = false;

        try{

            //Using resultSet to get all the records of a table
            ResultSet resultSet = getStatement().executeQuery("select * from data where Email = '" + customerUsername + "'");

            //checking whether query generates any records or not
            if(resultSet.next()){

                //checking whether the input matches the password (stored in table)
                if(customerPassword.equals(resultSet.getString("Password"))){

                    //if password matches the input
                    setCustomerUsername(customerUsername);  //setting the customerUsername
                    readNotification(customerUsername);   //reading notifications
                    checkLogin = true;  //changing variable to true
                }
                else{
                    System.out.println("##Incorrect password");
                }

            }
            //no record generated
            else{
                System.out.println("##'"+customerUsername+"' does not exist");
            }

        }
        catch(Exception e){  //throwing exception
            System.out.println("#####Login Error");
        }

        return checkLogin;  //returning variable
    }

    //reading notifications if there's any
    private void readNotification(String usernameNotification){

        //calling method from FunctionsDatabase class to get notifications
        String notification = new FunctionsDatabase().checkNotification(usernameNotification);

        //storing notifications in an array, after splitting
        String[] splittedNotification = notification.split(",");

        System.out.println("\n*************Notifications*************");

        //if there are notifications
        if(!Objects.equals(splittedNotification[0], "")) {

            //printing notifications
            for (int x = 0; x < splittedNotification.length; x++) {
                System.out.println((x + 1) + ". " + splittedNotification[x]);
            }
        }
        //if there is no notification
        else System.out.println("No notification");
        System.out.println("***************************************");
    }

}
