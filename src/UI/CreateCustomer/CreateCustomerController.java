package UI.CreateCustomer;

import BankSystem.BankSystem;
import UI.ErrorWindow;
import UI.Validators.CustomerValidator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateCustomerController
{
    private BankSystem _bankSystem;

    public TextField SSN_TextField;
    public TextField Address_TextField;
    public TextField City_TextField;
    public TextField State_TextField;
    public TextField Zipcode_TextField;
    public TextField FirstName_TextField;
    public TextField LastName_TextField;

    public void Create_OnMouseClick(MouseEvent mouseEvent)
    {
        boolean noErrors = true;
        String errorMessages = "";

        Stage stage = (Stage) SSN_TextField.getScene().getWindow();

        if (!CustomerValidator.SsnValidate(SSN_TextField.getText()))
        {
            noErrors = false;
            errorMessages = errorMessages.concat("SSN must be nine digits and be in the range of 100000000 through 999999999.\n");
        }

        if (!CustomerValidator.StateValidate(State_TextField.getText()))
        {
            noErrors = false;
            errorMessages = errorMessages.concat("State codes must be valid two char code. Ex: MO or NY.\n");
        }

        if (!CustomerValidator.ZipcodeValidate(Zipcode_TextField.getText()))
        {
            noErrors = false;
            errorMessages = errorMessages.concat("Zipcode must be five digits between 00000 and 99999.\n");
        }


        if (noErrors)
        {
            //No errors, make sure to replace on commas so the DB file cannot be corrupted.
            _bankSystem.CustomerManager.AddCustomer(SSN_TextField.getText().replace(",",""),
                                                    Address_TextField.getText().replace(",",""),
                                                    City_TextField.getText().replace(",",""),
                                                    State_TextField.getText().toUpperCase(),
                                                    Zipcode_TextField.getText(),
                                                    FirstName_TextField.getText().replace(",",""),
                                                    LastName_TextField.getText().replace(",",""));

            stage.close();
        }
        else
        {
            ErrorWindow errorWindow = new ErrorWindow(stage, errorMessages);
            errorWindow.ShowError();
        }
    }

    public void Cancel_OnMouseClick(MouseEvent mouseEvent)
    {
        Stage stage = (Stage) SSN_TextField.getScene().getWindow();
        stage.close();
    }

    public BankSystem get_bankSystem()
    {
        return _bankSystem;
    }

    public void set_bankSystem(BankSystem _bankSystem)
    {
        this._bankSystem = _bankSystem;
    }
}
