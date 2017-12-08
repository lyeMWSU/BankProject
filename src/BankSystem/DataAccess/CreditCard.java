package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;

import java.util.Date;

public class CreditCard extends Loan
{
    //region Variables
    private double limit;
    private String creditCardNumber;
    private int cvv;
    //endregion Variables

    //region Constructor
    public CreditCard(int accountId,
                      double balance,
                      String ssn,
                      double interestRate,
                      Date dateOpened,
                      Date dueDate,
                      Date dateNotified,
                      double currentPaymentDue,
                      Date lastPaymentDate,
                      boolean missedPayment,
                      double limit,
                      String creditCardNumber,
                      int cvv)
    {
        this.limit = limit;
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;

        super.setDueDate(dueDate);
        super.setDateNotified(dateNotified);
        super.setCurrentPaymentDue(currentPaymentDue);
        super.setLastPaymentDate(lastPaymentDate);
        super.setMissedPayment(missedPayment);

        super.setAccountId(accountId);
        super.setBalance(balance);
        super.setSsn(ssn);
        super.setInterestRate(interestRate);
        super.setAccountType(AccountType.CREDITCARD);
        super.setDateOpened(dateOpened);
    }
    //endregion Constructor

    //region Getters/Setters
    public double getLimit()
    {
        return limit;
    }

    public void setLimit(double limit)
    {
        this.limit = limit;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCvv()
    {
        return cvv;
    }

    public void setCvv(int cvv)
    {
        this.cvv = cvv;
    }
    //endregion Getters/Setters
}
//end CreditCard class
