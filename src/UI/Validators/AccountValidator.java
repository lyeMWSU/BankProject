package UI.Validators;

public class AccountValidator
{
    public static boolean ValidateInitialBalance(String initialBalance)
    {
        //Check to make sure the field isn't blank
        if (initialBalance.length() <= 0)
        {
            return false;
        }

        //Make sure all characters are numbers
        if (!checkAllNumbers(initialBalance, true))
        {
            return false;
        }

        //Check if the balance is within the valid range of a double
        try
        {
            if (Double.parseDouble(initialBalance) > Double.MAX_VALUE &&
                    Double.parseDouble(initialBalance) < Double.MIN_VALUE)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            //If an exception is thrown it is likely a double could not be parsed, so this is invalid
            return false;
        }

        //Everything passes, this is a valid initial balance
        return true;
    }

    public static boolean ValidateInterestRate(String interestRate)
    {
        //Check to make sure the field isn't blank
        if (interestRate.length() <= 0)
        {
            return false;
        }

        //Make sure all characters are numbers
        if (!checkAllNumbers(interestRate, true))
        {
            return false;
        }

        //Check if the balance is within the valid range of a double
        try
        {
            if (Double.parseDouble(interestRate) > Double.MAX_VALUE &&
                    Double.parseDouble(interestRate) < Double.MIN_VALUE)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            //If an exception is thrown it is likely a double could not be parsed, so this is invalid
            return false;
        }

        return true;
    }

    public static boolean ValidatePin(String pin)
    {
        //Check to make sure the field isn't blank and that it is four digits
        if (!(pin.length() <= 0))
        {
            return false;
        }

        if (pin.length() < 4 || pin.length() > 4)
        {
            return false;
        }

        //Make sure all characters are numbers
        if (!checkAllNumbers(pin, false))
        {
            return false;
        }

        //All passed, this must be a valid Pin
        return true;
    }

    public static boolean ValidateCardNumber(String cardNumber)
    {
        //Check to make sure the field isn't blank and that it is sixteen digits
        if (!(cardNumber.length() <= 0))
        {
            return false;
        }

        if (cardNumber.length() < 16 || cardNumber.length() > 16)
        {
            return false;
        }

        //Make sure all characters are numbers
        if (!checkAllNumbers(cardNumber, false))
        {
            return false;
        }

        //All passed, this must be a valid Card Number
        return true;
    }

    public static boolean ValidateCvv(String cvv)
    {
        //Check to make sure the field isn't blank and that it is three digits
        if (!(cvv.length() <= 0))
        {
            return false;
        }

        if (cvv.length() < 3 || cvv.length() > 3)
        {
            return false;
        }

        //Make sure all characters are numbers
        if (!checkAllNumbers(cvv, false))
        {
            return false;
        }

        //All passed, this must be a valid CVV
        return true;
    }

    public static boolean ValidateLimit(String limit)
    {
        //Check to make sure the field isn't blank
        if (limit.length() <= 0)
        {
            return false;
        }

        //Check if the balance is within the valid range of an int
        try
        {
            if (Integer.parseInt(limit) < Integer.MAX_VALUE &&
                    Integer.parseInt(limit) > Integer.MIN_VALUE)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            //If an exception is thrown it is likely an int could not be parsed, so this is invalid
            return false;
        }

        //Make sure all characters are numbers
        if (!checkAllNumbers(limit, true))
        {
            return false;
        }

        //All passed, this must be a valid Limit
        return true;
    }

    private static boolean checkAllNumbers(String checkString, boolean isDouble)
    {
        for (int i = 0; i < checkString.length(); i++)
        {
            if (isDouble && checkString.charAt(i) == '.') { continue; }
            if (!Character.isDigit(checkString.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }
}
