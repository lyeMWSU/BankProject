package UI.CustomerScreen;

import BankSystem.BankSystem;
import BankSystem.DataAccess.Account;
import BankSystem.DataAccess.Customer;
import UI.MainController;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerScreenController
{
    public Text CustomerName_Text;
    public ComboBox Account_ComboBox;
    public Text AccountBalance_Text;
    public Text AccountInterestRate_Text;
    public Text AccountStatus_Text;

    //The bank system data context
    private BankSystem _bankSystem;
    //endregion Variables




    Customer CustomerGotten;
    ArrayList<Account> CustomerAccounts;






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


    public void Account_ComboBox_SelectionChanged(ActionEvent actionEvent)
    {

            Account selectedAccount = (Account) Account_ComboBox.getValue();

            AccountBalance_Text.setText(Double.toString(selectedAccount.getBalance()));
            AccountInterestRate_Text.setText(Double.toString(selectedAccount.getInterestRate()));

            //Create method to determine the status of an account based on type.
            AccountStatus_Text.setText("OK");


    }

    public void RecentTransactions_OnMouseClick(MouseEvent mouseEvent)
    {
    }

    public void Withdraw_OnMouseClick(MouseEvent mouseEvent)
    {

    }

    public void Back_OnMouseClick(MouseEvent mouseEvent)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../MainScreen.fxml"));

        try
        {
            fxmlLoader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        //end of  try catch block

        Parent parent = fxmlLoader.getRoot();

        Stage stage = (Stage) CustomerName_Text.getScene().getWindow();
        stage.setTitle("Main Screen");
        //stage.setScene(new Scene(parent));

        //Use setRoot instead of setScene to prevent the window resizing.
        stage.getScene().setRoot(parent);

        MainController mainController = fxmlLoader.getController();
        mainController.set_bankSystem(_bankSystem);
    }
}
