package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.TransactionType;

import java.time.LocalDate;

public class Transaction
{
    //region Variables
    private int transactionId;
    private TransactionType transactionType;
    private String description;
    private LocalDate date;
    private double amount;
    private int accountId;
    //endregion Variables

    //region Constructor
    public Transaction(int transactionId,
                       TransactionType transactionType,
                       String description,
                       LocalDate date,
                       double amount,
                       int accountId)
    {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.accountId = accountId;
    }
    //endregion Constructor

    //region Getters/Setters
    public int getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(int transactionId)
    {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType)
    {
        this.transactionType = transactionType;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    //endregion Getters/Setters
}
