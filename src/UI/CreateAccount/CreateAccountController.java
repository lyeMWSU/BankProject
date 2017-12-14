package UI.CreateAccount;

import BankSystem.BankSystem;
import BankSystem.DataAccess.*;
import BankSystem.DataAccess.Enums.AccountType;
import BankSystem.DataAccess.Enums.TermLoanType;
import UI.ErrorWindow;
import UI.Validators.AccountValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CreateAccountController
{
    public TextField InitialBalance_TextField;
    public TextField InterestRate_TextField;
    public TextField CardNumber_TextField;
    public TextField PIN_TextField;
    public TextField CVV_TextField;
    public TextField Limit_TextField;
    public TextField Years_TextField;

    public Label InitialBalance_Label;
    public Label InterestRate_Label;
    public Label CardNumber_Label;
    public Label PIN_Label;
    public Label CVV_Label;
    public Label Limit_Label;
    public Label Years_Label;

    private BankSystem _bankSystem;
    private Customer _currentCustomer;

    public ComboBox AccountType_ComboBox;

    //Initialization for the Create Account controller. This is called when JavaFX first calls
    //the controller.
    public void initialize()
    {
        for (AccountType accountType : AccountType.values())
        {
            AccountType_ComboBox.getItems().add(accountType);
        }
    }

    public void set_currentCustomer(Customer _currentCustomer)
    {
        this._currentCustomer = _currentCustomer;
    }

    public void set_bankSystem(BankSystem _bankSystem)
    {
        this._bankSystem = _bankSystem;
    }

    //region Methods
    public void AccountType_OnSelectionChanged(ActionEvent actionEvent)
    {
        InitialBalance_Label.setVisible(false);
        InitialBalance_TextField.setVisible(false);

        InterestRate_Label.setVisible(false);
        InterestRate_TextField.setVisible(false);

        CardNumber_Label.setVisible(false);
        CardNumber_TextField.setVisible(false);

        PIN_Label.setVisible(false);
        PIN_TextField.setVisible(false);

        CVV_Label.setVisible(false);
        CVV_TextField.setVisible(false);

        Limit_Label.setVisible(false);
        Limit_TextField.setVisible(false);

        Years_Label.setVisible(false);
        Years_TextField.setVisible(false);

        switch((AccountType)AccountType_ComboBox.getValue())
        {
            case CHECKING:
                InitialBalance_Label.setVisible(true);
                InitialBalance_TextField.setVisible(true);

                InterestRate_Label.setVisible(true);
                InterestRate_TextField.setVisible(true);
                break;

            case SAVINGS:
                InitialBalance_Label.setVisible(true);
                InitialBalance_TextField.setVisible(true);

                InterestRate_Label.setVisible(true);
                InterestRate_TextField.setVisible(true);
                break;

            case ATM:
                CardNumber_Label.setVisible(true);
                CardNumber_TextField.setVisible(true);

                PIN_Label.setVisible(true);
                PIN_TextField.setVisible(true);
                break;

            case CREDITCARD:
                InterestRate_Label.setVisible(true);
                InterestRate_TextField.setVisible(true);

                CardNumber_Label.setVisible(true);
                CardNumber_TextField.setVisible(true);

                CVV_Label.setVisible(true);
                CVV_TextField.setVisible(true);

                Limit_Label.setVisible(true);
                Limit_TextField.setVisible(true);
                break;

            case CD:
                InitialBalance_Label.setVisible(true);
                InitialBalance_TextField.setVisible(true);

                InterestRate_Label.setVisible(true);
                InterestRate_TextField.setVisible(true);

            case TERMLOAN:
                InitialBalance_Label.setVisible(true);
                InitialBalance_TextField.setVisible(true);

                InterestRate_Label.setVisible(true);
                InterestRate_TextField.setVisible(true);

                Years_Label.setVisible(true);
                Years_TextField.setVisible(true);
                break;

        }
    }

    public void Create_OnMouseClick(MouseEvent mouseEvent)
    {
        //Keep track of error messages and if we have errors
        boolean noErrors = true;
        String errorMessages = "";

        //Grab the stage
        Stage stage = (Stage) InitialBalance_TextField.getScene().getWindow();

        switch((AccountType)AccountType_ComboBox.getValue())
        {
            //region CHECKING
            case CHECKING:
                if (!AccountValidator.ValidateInitialBalance(InitialBalance_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("initial balance error\n");
                }

                if (!AccountValidator.ValidateInterestRate(InterestRate_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("interest rate error\n");
                }

                if (noErrors)
                {
                    //Get today's date
                    LocalDate today = LocalDate.now();

                    boolean isGoldDiamond = Double.parseDouble(InitialBalance_TextField.getText()) > 10000.00;

                    Checking newCheckingAccount = new Checking(0,
                                                                Double.parseDouble(InitialBalance_TextField.getText()),
                                                                _currentCustomer.getSsn(),
                                                                Double.parseDouble(InterestRate_TextField.getText()),
                                                                today,
                                                                0,
                                                                isGoldDiamond);

                    _bankSystem.AccountManager.CreateAccount(newCheckingAccount);

                    stage.close();
                    return;
                }
                else { break; }
            //endregion CHECKING

            //region SAVINGS
            case SAVINGS:
                if (!AccountValidator.ValidateInitialBalance(InitialBalance_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("initial balance error\n");
                }

                if (!AccountValidator.ValidateInterestRate(InterestRate_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("interest rate error\n");
                }

                if (noErrors)
                {
                    //Get today's date
                    LocalDate today = LocalDate.now();

                    Savings newSavingsAccount = new Savings(0,
                                                              Double.parseDouble(InitialBalance_TextField.getText()),
                                                              _currentCustomer.getSsn(),
                                                              Double.parseDouble(InterestRate_TextField.getText()),
                                                              today,
                                                              0);

                    _bankSystem.AccountManager.CreateAccount(newSavingsAccount);

                    stage.close();
                    return;
                }
                else { break; }
            //endregion SAVINGS

            //region ATM
            case ATM:
                //Validate the card number
                if (!AccountValidator.ValidateCardNumber(CardNumber_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("card number error\n");
                }

                //Make sure the card number is not in use by another account.
                if (_bankSystem.AccountManager.CheckIfATMCardExists(CardNumber_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("card number exists error\n");
                }

                //Validate the PIN
                if (!AccountValidator.ValidatePin(PIN_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("pin error\n");
                }

                if (noErrors)
                {
                    //Get today's date
                    LocalDate today = LocalDate.now();

                    ATM newATMAccount = new ATM(0,
                                                _currentCustomer.getSsn(),
                                                today,
                                                Integer.parseInt(PIN_TextField.getText()),
                                                today,
                                                0,
                                                CardNumber_TextField.getText());

                    _bankSystem.AccountManager.CreateAccount(newATMAccount);

                    stage.close();
                    return;
                }
                else { break; }
            //endregion ATM

            //region CREDITCARD
            case CREDITCARD:
                //Validate the card number
                if (!AccountValidator.ValidateCardNumber(CardNumber_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("card number error\n");
                }

                //Make sure the card number is not in use by another account.
                if (_bankSystem.AccountManager.CheckIfCreditCardExists(CardNumber_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("card number exists error\n");
                }

                //Validate the CVV
                if (!AccountValidator.ValidateCvv(CVV_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("cvv error\n");
                }

                if (!AccountValidator.ValidateInterestRate(InterestRate_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("interest rate error\n");
                }

                //Validate the limit
                if (!AccountValidator.ValidateLimit(Limit_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("limit error\n");
                }

                if (noErrors)
                {
                    //Get today's date
                    LocalDate today = LocalDate.now();

                    //Set due date
                    LocalDate dueDate = LocalDate.now().plusMonths(1);

                    //Set notified date
                    LocalDate dateNotified = LocalDate.now();

                    //Set the last payment date
                    LocalDate lastPaymentDate = LocalDate.now();

                    CreditCard newCreditCardAccount = new CreditCard(0,
                                                                     0.0,
                                                                     _currentCustomer.getSsn(),
                                                                     Double.parseDouble(InterestRate_TextField.getText()),
                                                                     today,
                                                                     dueDate,
                                                                     dateNotified,
                                                                     0.0,
                                                                     lastPaymentDate,
                                                                     false,
                                                                     Double.parseDouble(Limit_TextField.getText()),
                                                                     CardNumber_TextField.getText(),
                                                                     Integer.parseInt(CVV_TextField.getText()));

                    _bankSystem.AccountManager.CreateAccount(newCreditCardAccount);

                    stage.close();
                    return;
                }
                else { break; }
            //endregion CREDITCARD

            //region CD
            case CD:
                if (!AccountValidator.ValidateInitialBalance(InitialBalance_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("initial balance error\n");
                }

                if (!AccountValidator.ValidateInterestRate(InterestRate_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("interest rate error\n");
                }

                if (noErrors)
                {
                    //Get today's date
                    LocalDate today = LocalDate.now();

                    //Set the start date
                    LocalDate startDate = LocalDate.now();

                    //Set the end date
                    LocalDate endDate = LocalDate.now().plusYears(1);

                    CD newCDAccount = new CD(0,
                                             Double.parseDouble(InitialBalance_TextField.getText()),
                                             _currentCustomer.getSsn(),
                                             Double.parseDouble(InterestRate_TextField.getText()),
                                             today,
                                             startDate,
                                             endDate);

                    _bankSystem.AccountManager.CreateAccount(newCDAccount);

                    stage.close();
                    return;
                }
                else { break; }
            //endregion CD

            //region TERMLOAN
            case TERMLOAN:
                //Validate the initial balance
                if (!AccountValidator.ValidateInitialBalance(InitialBalance_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("initial balance error\n");
                }

                //Validate the interest rate
                if (!AccountValidator.ValidateInterestRate(InterestRate_TextField.getText()))
                {
                    noErrors = false;
                    errorMessages = errorMessages.concat("interest rate error\n");
                }

                //TODO: Add validation for years field

                if (noErrors)
                {
                    //Get today's date
                    LocalDate today = LocalDate.now();

                    //Set due date
                    LocalDate dueDate = LocalDate.now().plusMonths(1);

                    //Set notified date
                    LocalDate dateNotified = LocalDate.now();

                    //Set the last payment date
                    LocalDate lastPaymentDate = LocalDate.now();

                    //Determine type of loan
                    TermLoanType loanType;
                    if (Integer.parseInt(Years_TextField.getText()) > 5)
                    {
                        loanType = TermLoanType.LONG;
                    }
                    else
                    {
                        loanType = TermLoanType.SHORT;
                    }

                    TermLoan newCreditCardAccount = new TermLoan(0,
                                                                 Double.parseDouble(InitialBalance_TextField.getText()),
                                                                 _currentCustomer.getSsn(),
                                                                 Double.parseDouble(InterestRate_TextField.getText()),
                                                                 today,
                                                                 dueDate,
                                                                 dateNotified,
                                                                 0.0,
                                                                 lastPaymentDate,
                                                                 false,
                                                                 loanType,
                                                                 Integer.parseInt(Years_TextField.getText()));

                    _bankSystem.AccountManager.CreateAccount(newCreditCardAccount);

                    stage.close();
                    return;
                }
                else { break; }
            //endregion TERMLOAN
        }

        //Display the input errors
        ErrorWindow errorWindow = new ErrorWindow(stage, errorMessages);
        errorWindow.ShowError();
    }

    public void Cancel_OnMouseClick(MouseEvent mouseEvent)
    {
        //Grab the stage and close
        Stage stage = (Stage) InitialBalance_TextField.getScene().getWindow();
        stage.close();
    }
    //endregion Methods
}
