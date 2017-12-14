package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.time.LocalDate;

public class ATM extends Account
{
    //region Variables
    private int pin;
    private LocalDate lastDateUsed;
    private int dailyUsageCount;
    private String cardNumber;
    //endregion Variables

    //region Constructor
    public ATM(int accountId,
               String ssn,
               LocalDate dateOpened,
               int pin,
               LocalDate lastDateUsed,
               int dailyUsageCount,
               String cardNumber)
    {
        this.pin = pin;
        this.lastDateUsed = lastDateUsed;
        this.dailyUsageCount = dailyUsageCount;
        this.cardNumber = cardNumber;

        super.setAccountId(accountId);
        super.setSsn(ssn);
        super.setAccountType(AccountType.ATM);
        super.setDateOpened(dateOpened);
    }
    //endregion Constructor

    //region Getters/Setters
    public int getPin()
    {
        return pin;
    }

    public void setPin(int pin)
    {
        this.pin = pin;
    }

    public LocalDate getLastDateUsed()
    {
        return lastDateUsed;
    }

    public void setLastDateUsed(LocalDate lastDateUsed)
    {
        this.lastDateUsed = lastDateUsed;
    }

    public int getDailyUsageCount()
    {
        return dailyUsageCount;
    }

    public void setDailyUsageCount(int dailyUsageCount)
    {
        this.dailyUsageCount = dailyUsageCount;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }
    //endregion Getters/Setters
}
