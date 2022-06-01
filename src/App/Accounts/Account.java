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

    @Override
    public String toString()
    {
        return  "uuid=" + uuid +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'';
    }
    
    @Override
    public int compareTo(Object o)
    {
        return this.getUuid().compareTo(((Account)o).getUuid());
    }
}
