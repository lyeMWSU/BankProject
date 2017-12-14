package UI.AtmCustomerChecker;

import BankSystem.BankSystem;
import BankSystem.DataAccess.Account;
import BankSystem.DataAccess.Customer;
import UI.ErrorWindow;
import UI.MainController;
import UI.Validators.CustomerValidator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AtmCustomerSearchController
{
    public TextField CusPinNum_Text;
    public TextField CusCardNum_Text;

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

    Account SelectedCustomer;
    ArrayList<Account> SelectedCustomerAccounts;


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

        Stage stage = (Stage) CusPinNum_Text.getScene().getWindow();
        stage.setTitle("Main Screen");
        //stage.setScene(new Scene(parent));

        //Use setRoot instead of setScene to prevent the window resizing.
        stage.getScene().setRoot(parent);

        MainController mainController = fxmlLoader.getController();
        mainController.set_bankSystem(_bankSystem);

    }


    public void Enter_OnMouseClick(MouseEvent mouseEvent) {

        if (_bankSystem.VerifyATM(CusCardNum_Text.getText(), CusPinNum_Text.getText()) != null)
        {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../CustomerScreen/CustomerScreen.fxml"));

            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //end of  try catch block

            Parent parent = fxmlLoader.getRoot();

            Stage stage = (Stage) CusPinNum_Text.getScene().getWindow();
            stage.setTitle("Bank Customer Screen");
            //stage.setScene(new Scene(parent));

            //Use setRoot instead of setScene to prevent the window resizing.
            stage.getScene().setRoot(parent);

        }
        else {
            ErrorWindow errorWindow = new ErrorWindow((Stage) CusCardNum_Text.getScene().getWindow(),
                    "Card Number not found.");
            errorWindow.ShowError();
        }

    }
    //end Enter_OnMouseClick
}
//end AtmCustomerSearchController
