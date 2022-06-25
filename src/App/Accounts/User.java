package App.Accounts;

import App.AppDate;
import App.Exceptions.FileErrorException;
import App.FilesHandler.UserGamesFile;
import App.Products.Product;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class User extends Account
{

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
                float wallet, ArrayList<Integer> whish, ArrayList<Integer> cart, ArrayList<Integer> library)
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

    public void addFounds(float newFounds)
    {
        this.wallet += newFounds;
    }

    //wishlist

    public void addWhishList(Integer num)
    {
        if (!searchWhishList(num))
        {
            this.wishList.add(num);
        }
    }

    public boolean searchWhishList(Integer num)
    {
            for (int i = 0; i < wishList.size(); i++)
            {
                if (wishList.get(i) == num) {
                    return true;
                }
            }

        return false;
    }

    public void removeWishList ( Integer num )
    {
        this.wishList.remove(num);
    }

    // Cart

    public void addCart ( Integer num )
    {
        if (!searchCart(num))
        {
            this.cart.add(num);
        }
    }

    public boolean searchCart ( Integer num )
        {

            for ( int i = 0; i< cart.size(); i++ )
            {
                if ( cart.get(i) == num)
                {
                    return true;
                }
            }

            return false;
        }


    public void removeCart ( Integer num )
    {
        this.cart.remove(num);
    }

    // library

    public void addLibrary ( Integer num )
    {
        if (!searchLibrary(num))
        {
            this.library.add(num);
        }
    }

    public boolean searchLibrary ( Integer num )
    {

        for ( int i = 0; i < library.size(); i++ )
        {
            if ( library.get(i) == num)
            {
                return true;
            }
        }

        return false;
    }

    public void removeLibrary ( Integer num )
    {
        this.library.remove(num);
    }

    //Autoriza la compra
    public boolean canBuy ( Product product )
    {
        boolean result = false;

        if ( product != null && product.getPrice() <= wallet )
        {
            result = true;
        }

        return result;
    }

     //Finaliza la compra
        public boolean finalizePurchase ( Product product)
        {

            boolean result = false;

            if ( canBuy(product) )
            {
                if (searchCart(product.getId()))
                {
                    removeCart(product.getId());
                }
                if (searchWhishList(product.getId()))
                {
                    removeWishList(product.getId());
                }

                addLibrary(product.getId());

                wallet -= product.getPrice();

                result = true;
            }

            return result;
        }




    @Override
    public String toString()
    {
        return  super.toString() +
                ", birthDate=" + birthDate +
                ", " + accountInfo;
    }

}



