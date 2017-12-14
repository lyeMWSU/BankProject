package UI;

import BankSystem.BankSystem;

import UI.AtmCustomerChecker.AtmCustomerSearchController;
import UI.BankManager.BankManagerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private BankSystem _bankSystem;

    public ComboBox UserType_ComboBox;

    public void initialize() {
        //Set the list of available users
        ObservableList<String> userTypeList = FXCollections.observableArrayList();
        userTypeList.addAll("Customer", "Teller", "Manager");
        UserType_ComboBox.setItems(userTypeList);

    }

    public BankSystem get_bankSystem() {
        return _bankSystem;
    }

    public void set_bankSystem(BankSystem _bankSystem) {
        this._bankSystem = _bankSystem;
    }

    public void Exit_OnMouseClick(MouseEvent mouseEvent) {
        _bankSystem.ExitCheckPoint();

        Stage stage = (Stage) UserType_ComboBox.getScene().getWindow();
        stage.close();
    }

    public void LoadUser_OnMouseClick(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();

        Stage stage = (Stage) UserType_ComboBox.getScene().getWindow();

        switch (UserType_ComboBox.getValue().toString()) {
            case "Manager":
                fxmlLoader.setLocation(getClass().getResource("BankManager/BankManager.fxml"));

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent managerParent = fxmlLoader.getRoot();

                stage.setTitle("Bank Manager Screen");
                //stage.setScene(new Scene(parent));

                //Use setRoot instead of setScene to prevent the window resizing.
                stage.getScene().setRoot(managerParent);

                //Initialize the controller and pass in the bank system.
                BankManagerController managerBankManagerController = fxmlLoader.getController();
                managerBankManagerController.IsUserManager(true);
                managerBankManagerController.set_bankSystem(_bankSystem);
                return;

            case "Teller":
                //Navigation for the teller screen
                fxmlLoader.setLocation(getClass().getResource("BankManager/BankManager.fxml"));

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent tellerParent = fxmlLoader.getRoot();

                stage.setTitle("Bank Teller Screen");
                //stage.setScene(new Scene(parent));

                //Use setRoot instead of setScene to prevent the window resizing.
                stage.getScene().setRoot(tellerParent);

                //Initialize the controller and pass in the bank system.
                BankManagerController tellerBankManagerController = fxmlLoader.getController();
                tellerBankManagerController.IsUserManager(false);
                tellerBankManagerController.set_bankSystem(_bankSystem);
                return;

            case "Customer":
                fxmlLoader.setLocation(getClass().getResource("AtmCustomerChecker/AtmCustomerSearch.fxml"));

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent customerAtmParent = fxmlLoader.getRoot();

                stage.setTitle("Customer Atm Screen");

                //Use setRoot instead of setScene to prevent the window resizing.
                stage.getScene().setRoot(customerAtmParent);

                AtmCustomerSearchController atmCustomerSearchController = fxmlLoader.getController();
                atmCustomerSearchController.set_bankSystem(_bankSystem);

        }
    }
}
