package BankSystem.DataAccess;

import java.util.Date;

public abstract class Loan extends Account
{
    //region Variables
    private Date dueDate;
    private Date dateNotified;
    private double currentPaymentDue;
    private Date lastPaymentDate;
    private boolean missedPayment;
    //endregion Variables

    //region Getters/Setters
    public Date getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public Date getDateNotified()
    {
        return dateNotified;
    }

    public void setDateNotified(Date dateNotified)
    {
        this.dateNotified = dateNotified;
    }

    public double getCurrentPaymentDue()
    {
        return currentPaymentDue;
    }

    public void setCurrentPaymentDue(double currentPaymentDue)
    {
        this.currentPaymentDue = currentPaymentDue;
    }

    public Date getLastPaymentDate()
    {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate)
    {
        this.lastPaymentDate = lastPaymentDate;
    }

    public boolean isMissedPayment()
    {
        return missedPayment;
    }

    public void setMissedPayment(boolean missedPayment)
    {
        this.missedPayment = missedPayment;
    }
    //endregion Getters/Setters
}
//end Loan Class