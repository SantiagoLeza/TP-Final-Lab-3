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
import App.Products.App;
import App.Products.Product;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    public Administrator createAdministrator(String mail, String pasword) throws BadInputException, NewUserException
    {
        UUID uuid = UUID.randomUUID();
        try {
            if (adminFile.adminFindMail(mail) != null && usersFile.userFindMail(mail) != null) {
                throw new NewUserException("The mail is already in use");
            }
            if (!mail.contains("@") && !mail.contains(".com")) {
                throw new BadInputException("The mail is not valid");
            }
            while (adminFile.administratorFindUUID(uuid) != null) {
                uuid = UUID.randomUUID();
            }

            Administrator newAdmin = new Administrator(mail, pasword, uuid);
            try {
                adminFile.writeBinary(adminFilePath, newAdmin);
            } catch (MissMatchClassException e) {
                e.printStackTrace();
            }
            return newAdmin;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Game createGame(String name, float price, ESRBClassification esrb, int year, int month, int day, boolean multiplayer,
                           String developer, String editor, float sizeGB, ArrayList<String> genres, ArrayList<String> labels,
                           ArrayList<String> language, ArrayList<String> platforms) throws BadInputException
    {
        Game newGame = new Game(
                name,
                price,
                esrb,
                new AppDate(day, month, year),
                multiplayer,
                developer,
                editor,
                sizeGB,
                genres,
                labels,
                language,
                platforms
        );
        products.addProduct(newGame.getId(), newGame);
        GamesFile.addProductToFile("./src/App/FilesHandler/products.json", newGame);

        return newGame;
    }

    public App createApp(String name, float price, ESRBClassification esrb, String function) throws BadInputException
    {
        App newApp = new App(
                name,
                price,
                esrb,
                function
        );
        products.addProduct(newApp.getId(), newApp);
        GamesFile.addProductToFile("./src/App/FilesHandler/products.json", newApp);

        return newApp;
    }

    public Account logIn(String mail, String password) throws BadInputException
    {
        try
        {
            Account user = usersFile.userFindMail(mail);
            if(user == null)
            {
                user = adminFile.adminFindMail(mail);
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

    public ArrayList<User> getAllUsers()
    {
        try {
            return usersFile.readBinary(usersFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User showUserthroughMail (String mail)
    {
        try {
            return usersFile.userFindMail(mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product getProductById(int id)
    {
        return products.getThroughId(id);
    }

    public Product getThroughName(String name)
    {
        return products.getThroughName(name);
    }

    public ArrayList<Product> getByGenre(String genre)
    {
        ArrayList<Product> matches = new ArrayList<>();
        for (Product product : products.treeToArray() )
        {
            if (product instanceof Game)
            {
                if( ((Game) product).getGenders().contains(genre))
                {
                    matches.add(product);
                }
            }
        }
        return matches;
    }

    public ArrayList<Product> getByRating(float rating)
    {
        ArrayList<Product> matches = new ArrayList<>();
        for (Product product : products.treeToArray() )
        {
            if( Math.round(product.getRating()) == rating )
            {
                matches.add(product);
            }
        }
        return matches;
    }

    public void removeThroughId (Integer id)
    {
        products.removeThroughID(id);
        GamesFile.deleteById(id);
    }
    
    public ArrayList <Product> getProducts ()
    {
        return products.treeToArray();
    }

    public void modifyProduct (Product product2)
    {
        GamesFile.modifyProduct(product2);
    }
    
    

}
