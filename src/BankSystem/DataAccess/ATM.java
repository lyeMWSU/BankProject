/*
The ATM class is part of the Account class. It includes a PIN, the last date the ATM was used for the user, it checks
how many times the user has accessed their account through an ATM, and their credit card. To access the ATM, the user
must have an ATM card that has a valid card number and a valid pin (when the user inputs it). The system will verify
the card and pin numbers, and if so, it will check the last date the card was used, and counts how many times it has
been used for the day.
*/




package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.util.Date;

public class ATM extends Account
{
    //region Variables
    private int pin;
    private Date lastDateUsed;
    private int dailyUsageCount;
    private String cardNumber;
    //endregion Variables

    //region Constructor
    public ATM(int accountId,
               String ssn,
               Date dateOpened,
               int pin,
               Date lastDateUsed,
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

    public Date getLastDateUsed()
    {
        return lastDateUsed;
    }

    public void setLastDateUsed(Date lastDateUsed)
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
//end ATM Class