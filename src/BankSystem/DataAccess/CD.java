package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.util.Date;

public class CD extends Account
{
    //region Variables
    private Date startDate;
    private Date endDate;
    //endregion Variables

    //region Constructor
    public CD(int accountId,
              double balance,
              String ssn,
              double interestRate,
              Date dateOpened,
              Date startDate,
              Date endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;

        super.setAccountId(accountId);
        super.setBalance(balance);
        super.setSsn(ssn);
        super.setInterestRate(interestRate);
        super.setAccountType(AccountType.CD);
        super.setDateOpened(dateOpened);
    }
    //endregion Constructor

    //region Getters/Setters
    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    //endregion Getters/Setters
}
//end CD Class
