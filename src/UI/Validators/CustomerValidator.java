package UI.Validators;

import UI.Validators.Enums.StateCode;

public class CustomerValidator
{
    public static boolean SsnValidate(String ssn)
    {
        //Check if SSN length is exactly nine characters
        if (ssn.length() != 9)
        {
            return false;
        }

        //Make sure all characters are numbers
        for (int i = 0; i < ssn.length(); i++)
        {
            if (!Character.isDigit(ssn.charAt(i)))
            {
                return false;
            }
        }

        //Check if the SSN is within the valid range of 100000000 through 999999999
        try
        {
            if (Integer.parseInt(ssn) < 100000000 && Integer.parseInt(ssn) > 999999999)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            //If an exception is thrown it is likely an int could not be parsed, so this is invalid
            return false;
        }

        //Everything passes, this is a valid SSN
        return true;
    }

    public static boolean StateValidate(String state)
    {
        for(StateCode stateCode : StateCode.values())
        {
            if (stateCode.toString().equals(state.toUpperCase()))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean ZipcodeValidate(String zipcode)
    {
        //Check if zipcode length is exactly five characters
        if (zipcode.length() != 5)
        {
            return false;
        }

        //Make sure all characters are numbers
        for (int i = 0; i < zipcode.length(); i++)
        {
            if (!Character.isDigit(zipcode.charAt(i)))
            {
                return false;
            }
        }

        //Check if the zipcode is within the valid range of 0 through 99999
        try
        {
            if (Integer.parseInt(zipcode) < 0 && Integer.parseInt(zipcode) > 99999)
            {
                return false;
            }
        }
        catch (Exception e)
        {
            //If an exception is thrown it is likely an int could not be parsed, so this is invalid
            return false;
        }

        //Everything passes, this is a valid zipcode
        return true;
    }
}
