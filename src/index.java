import App.Accounts.User;
import App.AppDate;
import App.Exceptions.BadInputException;
import App.Exceptions.NewUserException;
import App.FilesHandler.UserGamesFile;
import App.FilesHandler.UsersFile;
import App.Frames.UserMainFrame;
import App.Products.ESRBClassification;
import App.Products.Game;
import App.Steam;

import java.io.IOException;

public class index
{
    public static void main(String[] args)
    {
        UsersFile usersFile = new UsersFile();

        Steam steam = new Steam();

        Game g1 = new Game(
                "God of War",
                4500,
                ESRBClassification.T,
                new AppDate(2,2,2018),
                false,
                "Santa Monica",
                "PlayStation",
                70
        );

//        try {
//            steam.createUser("@.com", "123", "Jorge", "Lopez", "456654", "5555555", "USA", "Florida", "Miami", "Ocean View Av.", 2000, 2, 2);
//        } catch (NewUserException | BadInputException e) {
//            e.printStackTrace();
//        }

        try
        {
            User user = usersFile.userFindMail("@.com");
            //UserGamesFile.addProductLibrary(user.getUuid(), g1);
            if(user != null)
            {
                UserMainFrame mainFrame = new UserMainFrame(user);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
}

//steam.createUser("@.com", "123", "Jorge", "Lopez", "456654", "555-5555",
//                   "USA", "Florida", "Miami", "Ocean View Av.", 2000, 2, 2);