package SQL;

public class RegisterDatabase extends Connect{  //child class of Connect class

    //saving the customer's information in the SQL table
    public void registerCustomer(String customerFname, String customerLname, String customerEmail, String customerPassword, double customerAmount){

        //inserting records
        String QUERY = "insert into data VALUES ('"+customerFname+"','"+customerLname+"','"+customerEmail+"','"+customerPassword+"',"+customerAmount+",'')";

        try{

            //checking whether the account already exists or not
            if(getStatement().executeQuery("select * from data where email = '"+customerEmail+"'").next()){
                System.out.println("##'"+customerEmail+"' already exist");
            }
            else {  //if account does not exist, the QUERY would be executed.
                getStatement().executeUpdate(QUERY);
                System.out.println("##Account has been created successfully. You can log in to your account to proceed");
            }

        }catch(Exception e){  //throwing exception
            System.out.println("#####Registeration Error");
        }

    }
}
