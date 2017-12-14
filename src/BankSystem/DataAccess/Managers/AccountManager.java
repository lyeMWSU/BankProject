package BankSystem.DataAccess.Managers;

import BankSystem.DataAccess.*;
import BankSystem.DataAccess.Enums.AccountType;
import BankSystem.DataAccess.Enums.TermLoanType;
import BankSystem.DataAccess.Enums.TransactionType;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AccountManager
{
    //region Variables
    private ArrayList<Account> accounts;

    private int highestAccountId;
    private int highestTransactionId = 0;

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

        highestAccountId = getHighestId();
    }
    //endregion Constructor

    private int getHighestId()
    {
        int result = 1;

        //Get the highest Id for accounts
        for (Account account : accounts)
        {
            if (account.getAccountId() > result)
            {
                result = account.getAccountId();
            }
        }

        return result;
    }

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

    public void UpdateAccount(Account accountToUpdate)
    {
        accounts.removeIf(account -> account.getAccountId() == accountToUpdate.getAccountId());
        accounts.add(accountToUpdate);
    }

    public void CreateAccount(Account newAccount)
    {
        //Set the new AccountId and increment the highest Id
        newAccount.setAccountId(highestAccountId + 1);
        highestAccountId += 1;

        //Make a new ArrayList for transactions and store the initial transaction
        ArrayList<Transaction> transactions = new ArrayList<>();

        Transaction initialTransaction = new Transaction(highestTransactionId + 1,
                                                          TransactionType.INITIAL,
                                                          "Initial Account Setup",
                                                          LocalDate.now(),
                                                          newAccount.getBalance(),
                                                          newAccount.getAccountId());

        //Increment the highest transactionId by 1
        highestTransactionId += 1;

        //Add the new transaction to the transactions list and set this to the transactions of the account
        transactions.add(initialTransaction);

        newAccount.setTransactions(transactions);

        //Add the account
        accounts.add(newAccount);
    }

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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                LocalDate dateOpened = LocalDate.parse(splitLine[4]);
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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                LocalDate dateOpened = LocalDate.parse(splitLine[4]);
                int overdraftAccountId = Integer.parseInt(splitLine[5]);

                Savings savingsAccount = new Savings(accountId,
                                                     balance,
                                                     ssn,
                                                     interestRate,
                                                     dateOpened,
                                                     overdraftAccountId);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                savingsAccount.setTransactions(accountTransactions);

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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                LocalDate dateOpened = LocalDate.parse(splitLine[4]);
                LocalDate startDate = LocalDate.parse(splitLine[5]);
                LocalDate endDate = LocalDate.parse(splitLine[6]);

                CD cdAccount = new CD(accountId,
                                      balance,
                                      ssn,
                                      interestRate,
                                      dateOpened,
                                      startDate,
                                      endDate);

                ArrayList<Transaction> accountTransactions = transactionHashMap.get(accountId);
                cdAccount.setTransactions(accountTransactions);

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
    public Account GetATMAccountByCardNumber(String cardNumber)
    {
        Account result = null;

        for (Account account : accounts)
        {
            if (account.getAccountType() == AccountType.ATM && ((ATM)account).getCardNumber().equals(cardNumber))
            {
                return account;
            }
        }

        return result;
    }

    public boolean CheckIfATMCardExists(String cardNumber)
    {
        for (Account account : accounts)
        {
            if (account.getAccountType() == AccountType.ATM)
            {
                if (((ATM)account).getCardNumber().equals(cardNumber))
                {
                    //The card number exists
                    return true;
                }
            }
        }

        //The card number does not exist
        return false;
    }

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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int accountId = Integer.parseInt(splitLine[0]);
                String ssn = splitLine[1];
                LocalDate dateOpened = LocalDate.parse(splitLine[2]);
                int pin = Integer.parseInt(splitLine[3]);
                LocalDate lastDateUsed = LocalDate.parse(splitLine[4]);
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
    public boolean CheckIfCreditCardExists(String cardNumber)
    {
        for (Account account : accounts)
        {
            if (account.getAccountType() == AccountType.CREDITCARD)
            {
                if (((CreditCard)account).getCreditCardNumber().equals(cardNumber))
                {
                    //The card number exists
                    return true;
                }
            }
        }

        //The card number does not exist
        return false;
    }

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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                LocalDate dateOpened = LocalDate.parse(splitLine[4]);
                LocalDate dueDate = LocalDate.parse(splitLine[5]);
                LocalDate dateNotified = LocalDate.parse(splitLine[6]);
                double currentPaymentDue = Double.parseDouble(splitLine[7]);
                LocalDate lastPaymentDate = LocalDate.parse(splitLine[8]);
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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int accountId = Integer.parseInt(splitLine[0]);
                double balance = Double.parseDouble(splitLine[1]);
                String ssn = splitLine[2];
                double interestRate = Double.parseDouble(splitLine[3]);
                LocalDate dateOpened = LocalDate.parse(splitLine[4]);
                LocalDate dueDate = LocalDate.parse(splitLine[5]);
                LocalDate dateNotified = LocalDate.parse(splitLine[6]);
                double currentPaymentDue = Double.parseDouble(splitLine[7]);
                LocalDate lastPaymentDate = LocalDate.parse(splitLine[8]);
                boolean missedPayment = Boolean.parseBoolean(splitLine[9]);
                char loanType = splitLine[10].charAt(0);
                TermLoanType termLoanType;

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
    public void AddTransaction(Transaction transaction)
    {
        //Set the transaction Id to the next in line and increment the count
        transaction.setTransactionId(highestTransactionId + 1);
        highestTransactionId += 1;

        for (Account account : accounts)
        {
            if (account.getAccountId() == transaction.getAccountId())
            {
                if (account.getTransactions() == null)
                {
                    //No transactions exist, so make a new ArrayList and add transaction to it.
                    ArrayList<Transaction> transactions = new ArrayList<>();

                    transactions.add(transaction);

                    account.setTransactions(transactions);
                }
                else
                {
                    account.getTransactions().add(transaction);
                }
            }
        }
    }

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
                String currentLine = input.nextLine();

                String[] splitLine = currentLine.split(",");

                //DateFormat class to parse Date from file
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

                int transactionId = Integer.parseInt(splitLine[0]);

                //Set the highest TransactionId to the highest found while parsing
                if (transactionId > highestTransactionId)
                {
                    highestTransactionId = transactionId;
                }

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

                    case "I":
                        transactionType = TransactionType.INITIAL;
                        break;
                }

                String description = splitLine[2];
                LocalDate date = LocalDate.parse(splitLine[3]);
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
    public void ExitCheckPoint()
    {
        //Print writer to write output to
        PrintWriter output;

        //Array lists to store accounts and transactions being written out to files
        ArrayList<Checking> checkingAccounts = new ArrayList<>();
        ArrayList<Savings> savingsAccounts = new ArrayList<>();
        ArrayList<CD> cdAccounts = new ArrayList<>();
        ArrayList<ATM> atmAccounts = new ArrayList<>();
        ArrayList<CreditCard> creditCardAccounts = new ArrayList<>();
        ArrayList<TermLoan> termLoanAccounts = new ArrayList<>();

        ArrayList<Transaction> transactions = new ArrayList<>();

        for (Account account : accounts)
        {
            //Store all the transactions
            if (account.getTransactions() != null)
            {
                transactions.addAll(account.getTransactions());
            }

            //Sort the accounts for writing out to files
            switch(account.getAccountType())
            {
                case CHECKING:
                    checkingAccounts.add((Checking)account);
                    break;

                case SAVINGS:
                    savingsAccounts.add((Savings)account);
                    break;

                case CD:
                    cdAccounts.add((CD)account);
                    break;

                case ATM:
                    atmAccounts.add((ATM)account);
                    break;

                case CREDITCARD:
                    creditCardAccounts.add((CreditCard)account);
                    break;

                case TERMLOAN:
                    termLoanAccounts.add((TermLoan)account);
                    break;
            }
        }


        try
        {
            //Write Checking accounts
            output = new PrintWriter(new FileWriter("checking.txt"));

            for (Checking checkingAccount : checkingAccounts)
            {
                output.println(checkingAccount.getAccountId() + "," + checkingAccount.getBalance() + "," +
                        checkingAccount.getSsn() + "," + checkingAccount.getInterestRate() + "," +
                        checkingAccount.getDateOpened() + "," + checkingAccount.getOverdraftSavingsAccountId() + "," +
                        checkingAccount.isGoldDiamond());
            }

            output.close();

            //Write out Savings accounts
            output = new PrintWriter(new FileWriter("savings.txt"));

            for (Savings savingsAccount : savingsAccounts)
            {
                output.println(savingsAccount.getAccountId() + "," + savingsAccount.getBalance() + "," +
                        savingsAccount.getSsn() + "," + savingsAccount.getInterestRate() + "," +
                        savingsAccount.getDateOpened() + "," + savingsAccount.getOverdraftAccountId());
            }

            output.close();

            //Write out CD accounts
            output = new PrintWriter(new FileWriter("cd.txt"));

            for (CD cdAccount : cdAccounts)
            {
                output.println(cdAccount.getAccountId() + "," + cdAccount.getBalance() + "," +
                        cdAccount.getSsn() + "," + cdAccount.getInterestRate() + "," +
                        cdAccount.getDateOpened() + "," + cdAccount.getStartDate() + "," +
                        cdAccount.getEndDate());
            }

            output.close();

            //Write out ATM accounts
            output = new PrintWriter(new FileWriter("atm.txt"));

            for (ATM atmAccount : atmAccounts)
            {
                output.println(atmAccount.getAccountId() + "," +
                        atmAccount.getSsn() + "," + atmAccount.getDateOpened() + "," +
                        atmAccount.getPin() + "," + atmAccount.getLastDateUsed() + "," +
                        atmAccount.getDailyUsageCount() + "," + atmAccount.getCardNumber());
            }

            output.close();

            //Write out Credit Card accounts
            output = new PrintWriter(new FileWriter("creditcard.txt"));

            for (CreditCard creditCardAccount : creditCardAccounts)
            {
                output.println(creditCardAccount.getAccountId() + "," + creditCardAccount.getBalance() + "," +
                        creditCardAccount.getSsn() + "," + creditCardAccount.getInterestRate() + "," +
                        creditCardAccount.getDateOpened() + "," +
                        creditCardAccount.getDueDate() + "," + creditCardAccount.getDateNotified() + "," +
                        creditCardAccount.getCurrentPaymentDue() + "," + creditCardAccount.getLastPaymentDate() + "," +
                        creditCardAccount.isMissedPayment() + "," + creditCardAccount.getLimit() + "," +
                        creditCardAccount.getCreditCardNumber() + "," + creditCardAccount.getCvv());
            }

            output.close();

            //Write out Term Loan accounts
            output = new PrintWriter(new FileWriter("termloan.txt"));

            for (TermLoan termLoanAccount : termLoanAccounts)
            {
                output.println(termLoanAccount.getAccountId() + "," + termLoanAccount.getBalance() + "," +
                        termLoanAccount.getSsn() + "," + termLoanAccount.getInterestRate() + "," +
                        termLoanAccount.getDateOpened() + "," +
                        termLoanAccount.getDueDate() + "," + termLoanAccount.getDateNotified() + "," +
                        termLoanAccount.getCurrentPaymentDue() + "," + termLoanAccount.getLastPaymentDate() + "," +
                        termLoanAccount.isMissedPayment() + "," + termLoanAccount.getTermLoanType() + "," +
                        termLoanAccount.getYears());
            }

            output.close();

            //Write out all the account transactions
            output = new PrintWriter(new FileWriter("transactions.txt"));

            for (Transaction transaction : transactions)
            {
                //Parse the type string
                String transactionType = "";

                switch(transaction.getTransactionType())
                {
                    case DEBIT:
                        transactionType = "D";
                        break;

                    case CREDIT:
                        transactionType = "C";
                        break;

                    case TRANSFER:
                        transactionType = "T";
                        break;

                    case INITIAL:
                        transactionType = "I";
                }

                output.println(transaction.getTransactionId() + "," + transactionType + "," +
                transaction.getDescription() + "," + transaction.getDate() + "," +
                transaction.getAmount() + "," + transaction.getAccountId());
            }

            output.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //endregion Exit Check Point
}
