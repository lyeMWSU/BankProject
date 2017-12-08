/*
This program simulates of a bank system. This system has the capabilities to allow Bank Tellers to view customer
accounts status and the number and types of accounts the customer has. It also displays the Interest Rates and the
Balance. The bank teller can create accounts, view recent transactions, Make deposits or payments, make withdraws or
transfers.

At the end of every operation, the user must go back to the main
screen and click "exit" to save all changes.

*/


package UI;

import BankSystem.BankSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        //primaryStage.setTitle("Main Screen");
        //primaryStage.setScene(new Scene(root, 300, 275));

        BankSystem bankSystem = new BankSystem();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MainScreen.fxml"));
        fxmlLoader.load();

        Parent parent = fxmlLoader.getRoot();
        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene(parent, 590, 380));

        MainController mainController = fxmlLoader.getController();
        mainController.set_bankSystem(bankSystem);

        //Testing commit
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
