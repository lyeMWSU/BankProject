package UI.BankManager;

import BankSystem.BankSystem;
import BankSystem.DataAccess.Account;
import BankSystem.DataAccess.Customer;
import UI.CreateCustomer.CreateCustomerController;
import UI.ErrorWindow;
import UI.MainController;
import UI.Validators.CustomerValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class BankManagerController
{
    //region Variables

    //region Customer Widgets
    public Button CreateCustomer_Button;
    public TextField SSN_TextField;
    public Text CustomerName_Text;
    //endregion Customer Widgets

    //region Account Widgets
    public ComboBox<Account> Account_ComboBox;
    public Text AccountBalance_Text;
    public Text AccountInterestRate_Text;
    public Text AccountStatus_Text;
    //endregion Account Widgets

    //region Manager Grids
    public GridPane ManagerAccount_GridPane;
    public GridPane ManagerOptions_GridPane;
    //endregion Manager Grids

    Customer SelectedCustomer;
    ArrayList<Account> SelectedCustomerAccounts;

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

    public void IsUserManager(boolean isManager)
    {
        ManagerAccount_GridPane.setVisible(isManager);
        ManagerOptions_GridPane.setVisible(isManager);
    }

    //region Methods
    public void CreateCustomer_OnMouseClick(MouseEvent mouseEvent)
    {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../CreateCustomer/CreateCustomer.fxml"));
        try
        {
            fxmlLoader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Parent parent = fxmlLoader.getRoot();
        newStage.setTitle("Create Customer");
        newStage.setScene(new Scene(parent, 300, 300));

        CreateCustomerController createCustomerController = fxmlLoader.getController();
        createCustomerController.set_bankSystem(_bankSystem);

        //These methods set the parent stage to the previous stage so a second window can
        //be opened, but not allow the parent to continue until the child stage is done.
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(CreateCustomer_Button.getScene().getWindow());

        newStage.showAndWait();
    }
    //end CreateCustomer_OnMouseClick

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

        Stage stage = (Stage) CreateCustomer_Button.getScene().getWindow();
        stage.setTitle("Main Screen");
        //stage.setScene(new Scene(parent));

        //Use setRoot instead of setScene to prevent the window resizing.
        stage.getScene().setRoot(parent);

        MainController mainController = fxmlLoader.getController();
        mainController.set_bankSystem(_bankSystem);
    }
    //end Back_OnMouseClick

    public void Account_ComboBox_SelectionChanged(ActionEvent actionEvent)
    {
        //TODO: Populate account information when selection is made in combobox
        if (SelectedCustomer != null && SelectedCustomerAccounts != null)
        {
            Account selectedAccount = Account_ComboBox.getValue();

            AccountBalance_Text.setText(Double.toString(selectedAccount.getBalance()));
            AccountInterestRate_Text.setText(Double.toString(selectedAccount.getInterestRate()));

            //Create method to determine the status of an account based on type.
            AccountStatus_Text.setText("OK");
        }
        //end if.
        else
        {
            AccountBalance_Text.setText("");
            AccountInterestRate_Text.setText("");
            AccountStatus_Text.setText("");
        }
        //end else.
    }
    //end Account_ComboBox_SelectionChanged

    public void SearchCustomers_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement search customers button click method and populate customer data.
        if (CustomerValidator.SsnValidate(SSN_TextField.getText()))
        {
            Customer selectedCustomer = _bankSystem.CustomerManager.FindCustomer(SSN_TextField.getText());

            if (selectedCustomer != null)
            {
                SelectedCustomer = selectedCustomer;
                CustomerName_Text.setText(SelectedCustomer.getLastName() + ", " +
                        SelectedCustomer.getFirstName());

                ArrayList<Account> customerAccounts =
                        _bankSystem.AccountManager.GetCustomerAccounts(SelectedCustomer.getSsn());

                SelectedCustomerAccounts = null;

                Account_ComboBox.getItems().clear();

                SelectedCustomerAccounts = customerAccounts;

                for (Account account : SelectedCustomerAccounts)
                {
                    Account_ComboBox.getItems().add(account);
                }
                //end for loop
            }
            //end if.
            else
            {
                ErrorWindow errorWindow = new ErrorWindow((Stage)SSN_TextField.getScene().getWindow(),
                        "SSN not found.");
                errorWindow.ShowError();
            }
            //end else.
        }
        //end if.
        else
        {
            ErrorWindow errorwindow = new ErrorWindow((Stage)SSN_TextField.getScene().getWindow(),
                    "SSN must be nine digits and be in the range of 100000000 through 999999999.\n");
            errorwindow.ShowError();
        }
        //end else.
    }
    //end SearchCustomers_onMouseClick

    public void CreateAccount_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement the create account window for account creation.
    }

    public void RecentTransactions_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement recent transactions window.
    }

    public void DepositPay_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement deposit/pay pop up window.
    }

    public void Withdraw_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement withdraw pop up window.
    }

    public void Transfer_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement transfer pop up window.
    }

    //region Manager Only
    public void SetInterest_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement set interest pop up window.
    }

    public void CloseAccount_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement close account pop up.
    }

    public void SendRollOverNotices_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement send roll over notices pop up.
    }

    public void SendLoanCreditBills_OnMouseClick(MouseEvent mouseEvent)
    {
        //TODO: Implement send loan/credit bills pop up.
    }
    //endregion Manager Only
    //endregion Methods
}
