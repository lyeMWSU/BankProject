package UI.Withdraw;

import BankSystem.BankSystem;
import BankSystem.DataAccess.Account;
import BankSystem.DataAccess.Customer;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WithdrawController
{
    public Text CurrentBalance_Text;
    public TextField WithdrawAmount_TextField;

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

    public void PopulateCurrentBalance()
    {
        CurrentBalance_Text.setText("$" + Double.toString(_currentAccount.getBalance()));
    }

    public void Cancel_OnMouseClicked(MouseEvent mouseEvent)
    {
        Stage stage = (Stage) WithdrawAmount_TextField.getScene().getWindow();
        stage.close();
    }

    public void Withdraw_OnMouseClicked(MouseEvent mouseEvent)
    {
        //TODO:Implement withdraw from account. Just make sure the account can cover it.
        //Account type check should have happened before entering screen.
    }
}
