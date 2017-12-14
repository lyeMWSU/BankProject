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

    public Customer FindCustomer(String ssn)
    {
        for (Customer customer : customers)
        {
            if (customer.getSsn().equals(ssn))
            {
                return customer;
            }
        }

        return null;
    }

    public void AddCustomer(String ssn, String address, String city, String state,
                            String zipcode, String firstName, String lastName)
    {
        Customer newCustomer = new Customer(ssn, address, city, state,
                zipcode, firstName, lastName);

        customers.add(newCustomer);
    }

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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                Customer customer = new Customer(splitLine[0], splitLine[1], splitLine[2], splitLine[3],
                        splitLine[4], splitLine[5], splitLine[6]);

                customers.add(customer);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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
    }
}
