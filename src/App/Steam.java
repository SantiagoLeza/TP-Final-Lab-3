package App;

import App.Accounts.Adress;
import App.Accounts.User;
import App.Exceptions.BadInputException;
import App.Exceptions.FileErrorException;
import App.Exceptions.MissMatchClassException;
import App.Exceptions.NewUserException;
import App.FilesHandler.AdminFile;
import App.FilesHandler.UserGamesFile;
import App.FilesHandler.UsersFile;
import org.json.JSONException;

import java.io.IOException;
import java.util.UUID;

public class Steam
{
    String usersFilePath = "./src/App/FilesHandler/users.bin";
    
    private UsersFile usersFile;
    private AdminFile adminFile;

    public Steam()
    {
        usersFile = new UsersFile();
        adminFile = new AdminFile();
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
}
