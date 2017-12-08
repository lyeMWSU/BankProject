package BankSystem.DataAccess.Managers;

import BankSystem.DataAccess.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager
{
    private ArrayList<Customer> customers;

    //region Constructor
    public CustomerManager(String customerFileName)
    {
        customers = new ArrayList<>();
        loadCustomers(customerFileName);
    }
    //endregion Constructor

    //search function for searching for customer for the bank manager
    public Customer FindCustomer(String ssn)
    {
        for (Customer customer : customers)
        {
            //if the social security matches a customer, display the customer
            if (customer.getSsn().equals(ssn))
            {
                //displays customer if the SS matches.
                return customer;
            }
            //end if.
        }
        //end of for statement

        // if the social security is not found, display null.
        return null;
    }
    //end of FundCustomer search function

    //Constructor to add a new customer
    public void AddCustomer(String ssn, String address, String city, String state,
                            String zipcode, String firstName, String lastName)
    {
        Customer newCustomer = new Customer(ssn, address, city, state,
                zipcode, firstName, lastName);

        //add new customer...
        customers.add(newCustomer);
    }
    //end of constructor

    /* loadCustomers method loads the customer database file.
        * Requires a string for the name of the file to load.
    This method will load the customer database and populate the Customers ArrayList.*/
    private void loadCustomers(String pathName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(pathName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //reading...
                String currentLine = input.nextLine();

                //parse on commas...
                String[] splitLine = currentLine.split(",");

                Customer customer = new Customer(splitLine[0], splitLine[1], splitLine[2], splitLine[3],
                        splitLine[4], splitLine[5], splitLine[6]);

                //add customers...
                customers.add(customer);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //end of LoadCustomers

    /*this function is the exit button on the front screen. this function also saves everything
    the user has done and writes to a file if new information is recorded.*/
    public void ExitCheckPoint()
    {
        PrintWriter output;

        try
        {
            output = new PrintWriter(new FileWriter("customers.txt"));


            for (Customer customer : customers)
            {
                output.println(customer.getSsn() + "," + customer.getAddress() + "," + customer.getCity() + "," +
                        customer.getState() + "," + customer.getZipcode() + "," + customer.getFirstName() + "," +
                        customer.getLastName());
            }

            output.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        //end of catch
    }
    //end of ExitCheckPoint function
}
//end of CustomerManager
