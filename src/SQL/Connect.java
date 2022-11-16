package SQL;
import java.sql.*;
import java.sql.SQLException;

public class Connect {

    //storing customer username once they logged in successfully
    private static String customerUsername;


    //getting Statement
    protected Statement getStatement(){
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/database";  //url of the database
            String USERNAME = "username";  //username of the database
            String PASSWORD = "password";  //password for the database

            //connection to the database
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //creating statement that is responsible for executing queries.
            statement = connection.createStatement();

        }catch(SQLException e){  //throwing SQLException error
            System.out.println("#####Connectivity Error: "+e);
        }

        return statement;  //returning object statement
    }


    //customerUsername setter
    protected void setCustomerUsername(String username){

        customerUsername = username;
    }

    //customerUsername getter
    protected String getCustomerUsername(){
        return customerUsername;
    }

}
