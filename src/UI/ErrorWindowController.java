package UI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ErrorWindowController
{
    public Button OK_Button;
    public Label ErrorMessage_Label;

    public void setErrorMessage(String errorMessage)
    {
        ErrorMessage_Label.setText(errorMessage);
    }

    public void OK_OnMouseClick(MouseEvent mouseEvent)
    {
        Stage stage = (Stage) OK_Button.getScene().getWindow();
        stage.close();
    }
}