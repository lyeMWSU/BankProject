package UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ErrorWindow
{
    private Stage parentStage;
    private String errorMessage;

    public ErrorWindow(Stage parentStage, String errorMessage)
    {
        this.parentStage = parentStage;
        this.errorMessage = errorMessage;
    }

    public void ShowError()
    {
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ErrorWindow.fxml"));
        try
        {
            fxmlLoader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Parent parent = fxmlLoader.getRoot();
        newStage.setTitle("Error");
        newStage.setScene(new Scene(parent, 300, 300));

        ErrorWindowController errorWindowController = fxmlLoader.getController();
        errorWindowController.setErrorMessage(errorMessage);

        //These methods set the parent stage to the previous stage so a second window can
        //be opened, but not allow the parent to continue until the child stage is done.
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(parentStage);

        newStage.show();
    }
}
