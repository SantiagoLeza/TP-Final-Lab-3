package App.Accounts;

import java.util.UUID;

public class Developer extends Account
{

    public Developer(String mail, String password, String name, String surname, String ID, String phone, Adress adress, UUID uuid)
    {
        super(mail, password, uuid);
    }

    public void showAllUsers ()
    {

    }
}
