package App.Accounts;

import App.Exceptions.IDChangeNotValidException;

import java.util.UUID;

public abstract class Account implements Comparable
{
    private final UUID uuid;
    private String mail;
    private String password;

    public Account(String mail, String password, UUID uuid)
    {
        this.mail = mail;
        this.password = password;
        this.uuid = uuid;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public String getMail()
    {
        return mail;
    }

    public String getPassword()
    {
        return password;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return  "\n Uuid:" + uuid +
                "\n Mail:" + mail +
                "\n Password: " + password;
    }
    
    @Override
    public int compareTo(Object o)
    {
        return this.getUuid().compareTo(((Account)o).getUuid());
    }
}
