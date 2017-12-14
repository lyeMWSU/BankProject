package UI.RecentTransactions;

import BankSystem.BankSystem;
import BankSystem.DataAccess.Account;
import BankSystem.DataAccess.Customer;
import BankSystem.DataAccess.Transaction;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RecentTransactionsController
{
    //region Variables
    public TableView RecentTransactions_TableView;

    private Customer _currentCustomer;
    private Account _currentAccount;

    //The bank system data context
    private BankSystem _bankSystem;
    //endregion Variables

    //region Bank System Getter/Setter
    public BankSystem get_bankSystem()
    {
        return _bankSystem;
    }

    public void set_bankSystem(BankSystem _bankSystem)
    {
        this._bankSystem = _bankSystem;
    }
    //endregion Bank System Getter/Setter

    //region Current Customer Setter
    public void set_currentCustomer(Customer _currentCustomer)
    {
        this._currentCustomer = _currentCustomer;
    }
    //endregion Current Customer Setter

    //region Current Account Setter
    public void set_currentAccount(Account _currentAccount)
    {
        this._currentAccount = _currentAccount;
    }
    //endregion Current Account Setter

    public void PopulateTransactions()
    {
        for (Transaction transaction : _currentAccount.getTransactions())
        {
            RecentTransactions_TableView.getItems().add(transaction);
        }
    }

    public void OK_OnMouseClicked(MouseEvent mouseEvent)
    {
        //Close the window
        Stage stage = (Stage) RecentTransactions_TableView.getScene().getWindow();
        stage.close();
    }
}
