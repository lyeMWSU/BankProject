package BankSystem;

import BankSystem.DataAccess.ATM;
import BankSystem.DataAccess.Customer;
import BankSystem.DataAccess.Managers.AccountManager;
import BankSystem.DataAccess.Managers.CustomerManager;

import java.time.LocalDate;


public class BankSystem
{
    public CustomerManager CustomerManager;
    public AccountManager AccountManager;

    //region Constructor
    public BankSystem()
    {
        CustomerManager = new CustomerManager("customers.txt");
        AccountManager = new AccountManager("transactions.txt",
                                            "checking.txt",
                                            "savings.txt",
                                            "cd.txt",
                                            "atm.txt",
                                            "creditcard.txt",
                                            "termloan.txt");
    }
    //endregion Constructor

    public void ExitCheckPoint()
    {
        CustomerManager.ExitCheckPoint();
        AccountManager.ExitCheckPoint();
    }

    //The Verify ATM methods will return a customer if the card/pin number is valid,
    //and will return null if a customer is not found or the pin/card number is invalid.
    //If the card number is valid but the PIN is not, the attempt to access the account
    //will be noted.
    public Customer VerifyATM (String cardNumber, String pin)
    {
        ATM atmAccount = (ATM)AccountManager.GetATMAccountByCardNumber(cardNumber);

        try
        {
            if (atmAccount.getPin() == Integer.parseInt(pin))
            {
                //Get today's date
                //Calendar today = Calendar.getInstance();

                //Last date ATM account used
                LocalDate lastUsed = atmAccount.getLastDateUsed();

                //Check if the account has been used less than or equal to twice today.
                if (atmAccount.getDailyUsageCount() <= 2 ||
                        DateComesAfterDate(LocalDate.now(), lastUsed))
                {
                    //Update the daily usage
                    if (DateComesAfterDate(LocalDate.now(), lastUsed)) { atmAccount.setDailyUsageCount(1); }
                    atmAccount.setLastDateUsed(LocalDate.now());
                    atmAccount.setDailyUsageCount(atmAccount.getDailyUsageCount() + 1);
                    AccountManager.UpdateAccount(atmAccount);

                    return CustomerManager.FindCustomer(atmAccount.getSsn());
                }

                atmAccount.setLastDateUsed(LocalDate.now());
                atmAccount.setDailyUsageCount(atmAccount.getDailyUsageCount() + 1);
                AccountManager.UpdateAccount(atmAccount);
            }
            else
            {
                //Pin is incorrect, log this attempt
                //Update the daily usage

                atmAccount.setLastDateUsed(LocalDate.now());
                atmAccount.setDailyUsageCount(atmAccount.getDailyUsageCount() + 1);
                AccountManager.UpdateAccount(atmAccount);
            }
        }
        catch(Exception e)
        {
            return null;
        }

        return null;
    }

    //Wrote my own date comparison class because Java sucks at dates
    public boolean DateComesAfterDate(LocalDate currentDate, LocalDate previousDate)
    {
        int currentDateDay = currentDate.getDayOfYear();
        int currentDateYear = currentDate.getYear();

        int previousDateDay = previousDate.getDayOfYear();
        int previousDateYear = previousDate.getYear();

        return ((currentDateDay > previousDateDay) && (currentDateYear == previousDateYear)) ||
                (currentDateYear > previousDateYear);
    }

}
