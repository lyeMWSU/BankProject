package UI.CustomerScreen;

import BankSystem.BankSystem;
import BankSystem.DataAccess.Account;
import UI.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerScreenController
{

    //region Customer Account Widgets
    public ComboBox<Account> Account_ComboBox;
    public Text AccountBalance_Text;
    public Text AccountInterestRate_Text;
    public Text AccountStatus_Text;
    //endregion Customer Account Widgets
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



    public void Account_ComboBox_SelectionChanged(ActionEvent actionEvent)
    {
    }

    public void RecentTransactions_OnMouseClick(MouseEvent mouseEvent)
    {
    }

    public void Withdraw_OnMouseClick(MouseEvent mouseEvent)
    {
    }

    public void Back_OnMouseClick(MouseEvent mouseEvent)
    {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("../MainScreen.fxml"));
//
//        try
//        {
//            fxmlLoader.load();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        Parent parent = fxmlLoader.getRoot();

        //Stage stage = (Stage) CreateCustomer_Button.getScene().getWindow();
        //stage.setTitle("Main Screen");
        //stage.setScene(new Scene(parent));

        //Use setRoot instead of setScene to prevent the window resizing.
        //stage.getScene().setRoot(parent);

//        MainController mainController = fxmlLoader.getController();
//        mainController.set_bankSystem(_bankSystem);
    }


}
