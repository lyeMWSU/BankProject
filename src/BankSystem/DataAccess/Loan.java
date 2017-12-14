package BankSystem.DataAccess;

import java.time.LocalDate;

public abstract class Loan extends Account
{
    //region Variables
    private LocalDate dueDate;
    private LocalDate dateNotified;
    private double currentPaymentDue;
    private LocalDate lastPaymentDate;
    private boolean missedPayment;
    //endregion Variables

    //region Getters/Setters
    public LocalDate getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate)
    {
        this.dueDate = dueDate;
    }

    public LocalDate getDateNotified()
    {
        return dateNotified;
    }

    public void setDateNotified(LocalDate dateNotified)
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

    public LocalDate getLastPaymentDate()
    {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate)
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
