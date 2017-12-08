package BankSystem.DataAccess.Managers;

import BankSystem.DataAccess.*;
import BankSystem.DataAccess.Enums.TermLoanType;
import BankSystem.DataAccess.Enums.TransactionType;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class AccountManager
{
    //region Variables
    private ArrayList<Account> accounts;

    //Transaction array
    private HashMap<Integer, ArrayList<Transaction>> transactionHashMap = new HashMap<>();

    //endregion Variables

    //region Constructor
    public AccountManager(String transactionsFileName,
                          String checkingAccountFileName,
                          String savingsAccountFileName,
                          String cdAccountFileName,
                          String atmAccountFileName,
                          String creditCardAccountFileName,
                          String termLoanAccountFileName)
    {
        accounts = new ArrayList<>();

        //Load transactions first so the hash map is built
        loadTransactions(transactionsFileName);

        loadCheckingAccounts(checkingAccountFileName);
        loadSavingsAccounts(savingsAccountFileName);
        loadCDAccounts(cdAccountFileName);
        loadATMAccounts(atmAccountFileName);
        loadCreditCardAccounts(creditCardAccountFileName);
        loadTermLoanAccounts(termLoanAccountFileName);
    }
    //endregion Constructor

    //begin GetCustomerAccounts
    public ArrayList<Account> GetCustomerAccounts(String ssn)
    {
        ArrayList<Account> result = new ArrayList<>();

        for (Account account : accounts)
        {
            if (account.getSsn().equals(ssn))
            {
                result.add(account);
            }
        }

        return result;
    }
    //end GetCustomerAccounts...

    //region Checking
    private void loadCheckingAccounts(String checkingAccountFileName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(checkingAccountFileName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //reading...
                String currentLine = input.nextLine();

                //parse on commas
                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                //parsing out data...
                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                Date dateOpened = dateFormat.parse(splitLine[4]);
                int overdraftSavingsAccountId = Integer.parseInt(splitLine[5]);
                boolean isGoldDiamond = Boolean.parseBoolean(splitLine[6]);

                Checking checkingAccount = new Checking(accountId,
                                                        balance,
                                                        ssn,
                                                        interestRate,
                                                        dateOpened,
                                                        overdraftSavingsAccountId,
                                                        isGoldDiamond);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                checkingAccount.setTransactions(accountTransactions);

                //add checking account...
                accounts.add(checkingAccount);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion Checking

    //region Savings
    private void loadSavingsAccounts(String savingsAccountFileName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(savingsAccountFileName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //read...
                String currentLine = input.nextLine();

                //parse on commas...
                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                //parsing out data...
                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                Date dateOpened = dateFormat.parse(splitLine[4]);
                int overdraftAccountId = Integer.parseInt(splitLine[5]);

                Savings savingsAccount = new Savings(accountId,
                                                     balance,
                                                     ssn,
                                                     interestRate,
                                                     dateOpened,
                                                     overdraftAccountId);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                savingsAccount.setTransactions(accountTransactions);

                //add savings account...
                accounts.add(savingsAccount);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion Savings

    //region CD
    private void loadCDAccounts(String cdAccountFileName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(cdAccountFileName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //reading...
                String currentLine = input.nextLine();

                //parse on commas...
                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                //parse out data...
                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                Date dateOpened = dateFormat.parse(splitLine[4]);
                Date startDate = dateFormat.parse(splitLine[5]);
                Date endDate = dateFormat.parse(splitLine[6]);

                CD cdAccount = new CD(accountId,
                                            balance,
                                            ssn,
                                            interestRate,
                                            dateOpened,
                                            startDate,
                                            endDate);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                cdAccount.setTransactions(accountTransactions);

                //add CD Accounts...
                accounts.add(cdAccount);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion CD

    //region ATM
    private void loadATMAccounts(String atmAccountFileName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(atmAccountFileName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //reading...
                String currentLine = input.nextLine();

                //parse on commas...
                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                //parse out data...
                int accountId = Integer.parseInt(splitLine[0]);
                String ssn = splitLine[1];
                Date dateOpened = dateFormat.parse(splitLine[2]);
                int pin = Integer.parseInt(splitLine[3]);
                Date lastDateUsed = dateFormat.parse(splitLine[4]);
                int dailyUsageCount = Integer.parseInt(splitLine[5]);
                String cardNumber = splitLine[6];

                ATM atmAccount = new ATM(accountId,
                                         ssn,
                                         dateOpened,
                                         pin,
                                         lastDateUsed,
                                         dailyUsageCount,
                                         cardNumber);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                atmAccount.setTransactions(accountTransactions);

                //add ATM accounts...
                accounts.add(atmAccount);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion ATM

    //region Credit Card
    private void loadCreditCardAccounts(String creditCardAccountFileName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(creditCardAccountFileName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //read...
                String currentLine = input.nextLine();

                //parsing out on commas...
                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                //parsing out data.
                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                Date dateOpened = dateFormat.parse(splitLine[4]);
                Date dueDate = dateFormat.parse(splitLine[5]);
                Date dateNotified = dateFormat.parse(splitLine[6]);
                double currentPaymentDue = Double.parseDouble(splitLine[7]);
                Date lastPaymentDate = dateFormat.parse(splitLine[8]);
                boolean missedPayment = Boolean.parseBoolean(splitLine[9]);
                double limit = Double.parseDouble(splitLine[10]);
                String creditCardNumber = splitLine[11];
                int cvv = Integer.parseInt(splitLine[12]);

                CreditCard creditCardAccount = new CreditCard(accountId,
                                                              balance,
                                                              ssn,
                                                              interestRate,
                                                              dateOpened,
                                                              dueDate,
                                                              dateNotified,
                                                              currentPaymentDue,
                                                              lastPaymentDate,
                                                              missedPayment,
                                                              limit,
                                                              creditCardNumber,
                                                              cvv);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                creditCardAccount.setTransactions(accountTransactions);

                //add credit cards
                accounts.add(creditCardAccount);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion Credit Card

    //region Term Loan
    private void loadTermLoanAccounts(String termLoanAccountFileName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(termLoanAccountFileName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //reading...
                String currentLine = input.nextLine();

                //parsing out on commas...
                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                //parsing out data...
                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                Date dateOpened = dateFormat.parse(splitLine[4]);
                Date dueDate = dateFormat.parse(splitLine[5]);
                Date dateNotified = dateFormat.parse(splitLine[6]);
                double currentPaymentDue = Double.parseDouble(splitLine[7]);
                Date lastPaymentDate = dateFormat.parse(splitLine[8]);
                boolean missedPayment = Boolean.parseBoolean(splitLine[9]);
                char loanType = splitLine[10].charAt(0);
                TermLoanType termLoanType;

                //this determines whether the loan is long or short.
                if (loanType == 'S')
                {
                    termLoanType = TermLoanType.SHORT;
                }
                else
                {
                    termLoanType = TermLoanType.LONG;
                }

                int years = Integer.parseInt(splitLine[11]);

                TermLoan termLoanAccount = new TermLoan(accountId,
                                                        balance,
                                                        ssn,
                                                        interestRate,
                                                        dateOpened,
                                                        dueDate,
                                                        dateNotified,
                                                        currentPaymentDue,
                                                        lastPaymentDate,
                                                        missedPayment,
                                                        termLoanType,
                                                        years);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                termLoanAccount.setTransactions(accountTransactions);

                //adds term loan accounts
                accounts.add(termLoanAccount);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion Term Loan

    //region Transaction
    private void loadTransactions(String transactionFileName)
    {
        try
        {
            /* Open the file to read */
            File inputFile = new File(transactionFileName);

            /* Create a scanner to begin reading from file */
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine())
            {
                //read...
                String currentLine = input.nextLine();

                //parse on commas...
                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int transactionId = Integer.parseInt(splitLine[0]);

                String accountTypeChar = splitLine[1];
                TransactionType transactionType = null;
                switch (accountTypeChar)
                {
                    case "D":
                        transactionType = TransactionType.DEBIT;
                        break;

                    case "C":
                        transactionType = TransactionType.CREDIT;
                        break;

                    case "T":
                        transactionType = TransactionType.TRANSFER;
                }

                String description = splitLine[2];
                Date date = dateFormat.parse(splitLine[3]);
                double amount = Double.parseDouble(splitLine[4]);
                int accountId = Integer.parseInt(splitLine[5]);

                Transaction transaction = new Transaction(transactionId,
                                                          transactionType,
                                                          description,
                                                          date,
                                                          amount,
                                                          accountId);

                transactionHashMap.putIfAbsent(accountId, new ArrayList<>());
                transactionHashMap.get(accountId).add(transaction);
            }

            input.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion Transaction


    //region Exit Check Point
    /*TODO: Create an exit check point method that will take the updated account and transaction information
     *      and push them back out to the database files before the program exits.
     */
    //endregion Exit Check Point
}
