package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.util.Date;

public class Savings extends Account
{
    //region Variables
    private int overdraftAccountId;
    //endregion Variables

    //region Constructor
    public Savings(int accountId,
                   double balance,
                   String ssn,
                   double interestRate,
                   Date dateOpened,
                   int overdraftAccountId)
    {
        this.overdraftAccountId = overdraftAccountId;

        super.setAccountId(accountId);
        super.setBalance(balance);
        super.setSsn(ssn);
        super.setInterestRate(interestRate);
        super.setAccountType(AccountType.SAVINGS);
        super.setDateOpened(dateOpened);
    }
    //endregion Constructor

    //region Getters/Setters
    public int getOverdraftAccountId()
    {
        return overdraftAccountId;
    }

    public void setOverdraftAccountId(int overdraftAccountId)
    {
        this.overdraftAccountId = overdraftAccountId;
    }
    //endregion Getters/Setters
}
//end Savings class
