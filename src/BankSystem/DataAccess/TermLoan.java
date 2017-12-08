package BankSystem.DataAccess;

import BankSystem.DataAccess.Enums.AccountType;
import BankSystem.DataAccess.Enums.TermLoanType;

import java.util.Date;

public class TermLoan extends Loan
{
    //region Variables
    private TermLoanType termLoanType;
    private int years;
    //endregion Variables

    //region Constructor
    public TermLoan(int accountId,
                    double balance,
                    String ssn,
                    double interestRate,
                    Date dateOpened,
                    Date dueDate,
                    Date dateNotified,
                    double currentPaymentDue,
                    Date lastPaymentDate,
                    boolean missedPayment,
                    TermLoanType termLoanType,
                    int years)
    {
        this.termLoanType = termLoanType;
        this.years = years;

        super.setDueDate(dueDate);
        super.setDateNotified(dateNotified);
        super.setCurrentPaymentDue(currentPaymentDue);
        super.setLastPaymentDate(lastPaymentDate);
        super.setMissedPayment(missedPayment);

        super.setAccountId(accountId);
        super.setBalance(balance);
        super.setSsn(ssn);
        super.setInterestRate(interestRate);
        super.setAccountType(AccountType.TERMLOAN);
        super.setDateOpened(dateOpened);
    }
    //endregion Constructor

    //region Getters/Setters
    public TermLoanType getTermLoanType()
    {
        return termLoanType;
    }

    public void setTermLoanType(TermLoanType termLoanType)
    {
        this.termLoanType = termLoanType;
    }

    public int getYears()
    {
        return years;
    }

    public void setYears(int years)
    {
        this.years = years;
    }
    //endregion Getters/Setters
}
//end TermLoan Class
