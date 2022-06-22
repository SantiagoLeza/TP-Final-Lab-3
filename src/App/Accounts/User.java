package App.Accounts;

import App.AppDate;
import App.Exceptions.FileErrorException;
import App.FilesHandler.UserGamesFile;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class User extends Account {

    private AppDate birthDate;
    private AccountInfo accountInfo;
    private ArrayList<Integer> wishList;
    private ArrayList<Integer> cart;
    private ArrayList<Integer> library;
    private float wallet;

    public User(String mail, String password, String name, String surname, String ID, String phone, Adress adress, UUID uuid, AppDate birthDate)
    {
        super(mail, password, uuid);
        this.accountInfo = new AccountInfo(name, surname, ID, phone, adress);
        this.birthDate = birthDate;
        this.wishList = new ArrayList<>();
        this.cart = new ArrayList<>();
        this.library = new ArrayList<>();
        this.wallet = 0;
    }


    public User(String mail, String password, String name, String surname, String ID, String phone, Adress adress, UUID uuid, AppDate birthDate,
                float wallet, ArrayList<Integer> whish, ArrayList<Integer> cart, ArrayList<Integer> library )
        {
            super(mail, password, uuid);
            this.accountInfo = new AccountInfo(name, surname, ID, phone, adress);
            this.birthDate = birthDate;
            this.wishList = whish;
            this.cart = cart;
            this.library = library;
            this.wallet = wallet;
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

    public float getWallet()
    {
        return wallet;
    }

    public void setWallet(float wallet)
    {
        this.wallet = wallet;
    }

    public void addFounds (float newFounds )
    {
        this.wallet += newFounds;
    }

    public void addWhisList ( Integer num )
    {
       this.wishList.add(num);
    }
    
    public void addCart ( Integer num ) 
    {
        this.cart.add(num);
    }
    
    public void addLibrary ( Integer num )
    {
        this.library.add(num);
    }
    
   

    @Override
    public String toString()
    {
        return  super.toString() +
                ", birthDate=" + birthDate +
                ", " + accountInfo;
    }
    
}



