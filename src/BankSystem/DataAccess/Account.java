/*
This Account class is for display purposes for the customer. It shall include the Balance, the Interest Rate, a list
of Transactions, and will display the Account Type. The account class connects to several classes such as the ATM, CD,
Checking, Savings, and Loans.
*/
package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.util.ArrayList;
import java.util.Date;

public abstract class Account
{
    //region Variables
    private int accountId;
    private double balance;
    private String ssn;
    private double interestRate;
    private ArrayList<Transaction> transactions;
    private AccountType accountType;
    private Date dateOpened;
    //endregion Variables

    //region Getters/Setters
    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public String getSsn()
    {
        return ssn;
    }

    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate = interestRate;
    }

    public ArrayList<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions)
    {
        this.transactions = transactions;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    public void setAccountType(AccountType accountType)
    {
        this.accountType = accountType;
    }

    public Date getDateOpened()
    {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened)
    {
        this.dateOpened = dateOpened;
    }
    //endregion Getters/Setters

    @Override
    public String toString()
    {
        return this.accountType + " - " + this.accountId;
    }
}
//end Account Class
