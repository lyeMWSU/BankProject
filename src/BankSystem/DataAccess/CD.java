package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.time.LocalDate;

public class CD extends Account
{
    //region Variables
    private LocalDate startDate;
    private LocalDate endDate;
    //endregion Variables

    //region Constructor
    public CD(int accountId,
              double balance,
              String ssn,
              double interestRate,
              LocalDate dateOpened,
              LocalDate startDate,
              LocalDate endDate)
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
    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }
    //endregion Getters/Setters
}
