package App.Accounts;

import App.Exceptions.IDChangeNotValidException;

public class AccountInfo
{
    private String name;
    private String surname;
    private String ID;
    private String phone;
    private Adress adress;

    private boolean hadChangedId;

    /**
     * Use this constructor for new accounts.
     */
    public AccountInfo(String name, String surname, String ID, String phone, Adress adress)
    {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.phone = phone;
        this.adress = adress;

        hadChangedId = false;
    }

    /**
     * Use this constructor whien importing accounts from file.
     */
    public AccountInfo(String name, String surname, String ID, String phone, Adress adress, boolean hadChangedId)
    {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.phone = phone;
        this.adress = adress;

        this.hadChangedId = hadChangedId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Adress getAdress()
    {
        return adress;
    }

    public void setAdress(Adress adress)
    {
        this.adress = adress;
    }

    private void changeId(String id) throws IDChangeNotValidException, NumberFormatException
    {
        if(!hadChangedId)
        {
            try
            {
                Integer.parseInt(id);
                ID = id;
                hadChangedId = true;
            }
            catch (NumberFormatException e)
            {
                throw new NumberFormatException("ID must be a number");
            }
        }
        else
        {
            throw new IDChangeNotValidException("ID can only be changed once");
        }
    }

    @Override
    public String toString()
    {
        return  "\n Name: " + name +
                "\n Surname: " + surname +
                "\n ID: " + ID +
                "\n phone: " + phone +
                 adress.toString();
    }
}
