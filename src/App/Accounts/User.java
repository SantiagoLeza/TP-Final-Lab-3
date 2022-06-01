package App.Accounts;

import App.AppDate;

import java.util.Date;
import java.util.UUID;

public class User extends Account {

    private final AppDate birthDate;
    private AccountInfo accountInfo;

    public User(String mail, String password, String name, String surname, String ID, String phone, Adress adress, UUID uuid, AppDate birthDate)
    {
        super(mail, password, uuid);
        this.accountInfo = new AccountInfo(name, surname, ID, phone, adress);
        this.birthDate = birthDate;
    }

    public String getName()
    {
        return accountInfo.getName();
    }

    public String getSurname()
    {
        return accountInfo.getSurname();
    }

    public String getID()
    {
        return accountInfo.getID();
    }

    public String getPhone()
    {
        return accountInfo.getPhone();
    }

    public Adress getAdress()
    {
        return accountInfo.getAdress();
    }

    public void setName(String name)
    {
        accountInfo.setName(name);
    }

    public AppDate getBirthDate()
    {
        return birthDate;
    }

    public void setSurname(String surname)
    {
        accountInfo.setSurname(surname);
    }

    public void setPhone(String phone)
    {
        accountInfo.setPhone(phone);
    }

    public void setAdress(Adress adress)
    {
        accountInfo.setAdress(adress);
    }

    @Override
    public String toString()
    {
        return  super.toString() +
                ", birthDate=" + birthDate +
                ", " + accountInfo;
    }
}
