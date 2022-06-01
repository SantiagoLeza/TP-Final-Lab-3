package App.Accounts;

import java.util.UUID;

public class Administrator extends Account{

    

    public Administrator(String mail, String password, String name, String surname, String ID, String phone, Adress adress, UUID uuid)
    {
        super(mail, password, uuid);
    }
}
