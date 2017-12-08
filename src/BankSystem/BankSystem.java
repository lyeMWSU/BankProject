package BankSystem;

import BankSystem.DataAccess.Managers.AccountManager;
import BankSystem.DataAccess.Managers.CustomerManager;


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
    }

}
