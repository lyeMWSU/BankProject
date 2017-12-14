package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Account
{
    //region Variables
    private int accountId;
    private double balance;
    private String ssn;
    private double interestRate;
    private ArrayList<Transaction> transactions;
    private AccountType accountType;
    private LocalDate dateOpened;
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

    public LocalDate getDateOpened()
    {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened)
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
