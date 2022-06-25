package App;

import App.Accounts.Account;
import App.Accounts.Administrator;
import App.Accounts.Adress;
import App.Accounts.User;
import App.Exceptions.BadInputException;
import App.Exceptions.FileErrorException;
import App.Exceptions.MissMatchClassException;
import App.Exceptions.NewUserException;
import App.FilesHandler.AdminFile;
import App.FilesHandler.GamesFile;
import App.FilesHandler.UserGamesFile;
import App.FilesHandler.UsersFile;
import App.Products.ESRBClassification;
import App.Products.Game;
import App.Products.Product;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.UUID;

public class Steam
{
    String usersFilePath = "./src/App/FilesHandler/users.bin";
    String adminFilePath = "./src/App/FilesHandler/admin.bin";

    String gamesFilePath = "./src/App/FilesHandler/products.json";
    
    private UsersFile usersFile;
    private AdminFile adminFile;

    private ColeccionGenerica<Integer, Product> products;

    public Steam()
    {
        usersFile = new UsersFile();
        adminFile = new AdminFile();

        try{
            BufferedReader bf = new BufferedReader(new FileReader(gamesFilePath));

            products = GamesFile.fileToTree2(gamesFilePath);

        }
        catch (FileNotFoundException e)
        {
            products = new ColeccionGenerica<>();
        } catch (FileErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public User createUser(String mail, String password, String name, String surname, String ID,String phone, String country, String state, String city, String street, int year, int month, int day) throws NewUserException, BadInputException
    {
        UUID uuid = UUID.randomUUID();
        try
        {
            if(usersFile.userFindMail(mail) != null)
            {
                throw new NewUserException("The mail is already in use");
            }
            if(!mail.contains("@") && !mail.contains(".com"))
            {
                throw new BadInputException("The mail is not valid");
            }
            try {
                Integer.parseInt(phone);
            }
            catch (NumberFormatException e)
            {
                throw new BadInputException("The phone is not valid");
            }
            if(usersFile.userFindPhone(phone) != null)
            {
                throw new NewUserException("The phone is already in use");
            }
            if (usersFile.userFindID(ID) != null)
            {
                throw new NewUserException("The id is already in use");
            }
            while(usersFile.userFindUUID(uuid) != null)
            {
                uuid = UUID.randomUUID();
            }
            
            User newUser = new User(mail, password, name, surname, ID, phone, new Adress(country, state, city, street), uuid, new AppDate(day, month, year)); 
            try {
                usersFile.writeBinary(usersFilePath, newUser);
            }
            catch (MissMatchClassException e)
            {
                e.printStackTrace();
            }

            UserGamesFile.addUser(uuid);

            return newUser;
        }
        catch (IOException | FileErrorException | JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Game createGame(String name, float price, ESRBClassification esrb, int year, int month, int day, boolean multiplayer,
                           String developer, String editor, float sizeGB) throws BadInputException
    {
        Game newGame = new Game(
                name,
                price,
                esrb,
                new AppDate(day, month, year),
                multiplayer,
                developer,
                editor,
                sizeGB
        );
        GamesFile.addProductToFile("./src/App/FilesHandler/products.json", newGame);

        return newGame;
    }

    public Account logIn(String mail, String password) throws BadInputException
    {
        try
        {
            Account user = usersFile.userFindMail(mail);
            if(user == null)
            {
                user = adminFile.userFindMail(mail);
            }
            if(user == null)
            {
                throw new BadInputException("The mail is not in use");
            }
            if(!user.getPassword().equals(password))
            {
                throw new BadInputException("The password is not correct");
            }
            return user;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void modifyAccountUser (User user)
    {
        try
        {
            usersFile.userUpdate(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyAccountAdministrator (Administrator admin)
    {
        try
        {
            adminFile.adminUpdate(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
