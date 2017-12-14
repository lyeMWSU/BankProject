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
