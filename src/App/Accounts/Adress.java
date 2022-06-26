package App.Accounts;

public class Adress
{
    private String country;
    private String state;
    private String city;
    private String street;

    public Adress(String country, String state, String city, String street)
    {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    @Override
    public String toString()
    {
        return "\n Country: " + country +
                "\n State: " + state +
                "\n City='" + city +
                "\n Street='" + street;
    }
}
