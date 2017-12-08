package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.util.Date;

public class Checking extends Account
{
    //region Variables
    private int overdraftSavingsAccountId;
    private boolean isGoldDiamond;
    //endregion Variables

    //region Constructor
    public Checking(int accountId,
                    double balance,
                    String ssn,
                    double interestRate,
                    Date dateOpened,
                    int overdraftSavingsAccountId,
                    boolean isGoldDiamond)
    {
        this.overdraftSavingsAccountId = overdraftSavingsAccountId;
        this.isGoldDiamond = isGoldDiamond;

        super.setAccountId(accountId);
        super.setBalance(balance);
        super.setSsn(ssn);
        super.setInterestRate(interestRate);
        super.setAccountType(AccountType.CHECKING);
        super.setDateOpened(dateOpened);
    }
    //endregion Constructor

    //region Getters/Setters
    public int getOverdraftSavingsAccountId()
    {
        return overdraftSavingsAccountId;
    }

    public void setOverdraftSavingsAccountId(int overdraftAccountId)
    {
        this.overdraftSavingsAccountId = overdraftAccountId;
    }

    public boolean isGoldDiamond()
    {
        return isGoldDiamond;
    }

    public void setGoldDiamond(boolean goldDiamond)
    {
        isGoldDiamond = goldDiamond;
    }
    //endregion Getters/Setters
}
//end Checking Class
