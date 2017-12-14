package BankSystem.DataAccess;


public class Customer
{
    //region Variables
    private String ssn;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String firstName;
    private String lastName;
    //endregion Variables

    //region Constructor
    public Customer (String ssn,
                     String address,
                     String city,
                     String state,
                     String zipcode,
                     String firstName,
                     String lastName)
    {
        this.ssn = ssn;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    //endregion Constructor

    //region Getters/Setters
    public String getSsn()
    {
        return ssn;
    }

    public void setSsn(String ssn)
    {
        this.ssn = ssn;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    //endregion Getters/Setters
}