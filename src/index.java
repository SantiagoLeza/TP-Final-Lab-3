import App.Accounts.Account;
import App.Accounts.Administrator;
import App.Accounts.User;
import App.FilesHandler.UsersFile;
import App.Frames.AdminMainFrame;
import App.Frames.LogInFrame;
import App.Frames.UserMainFrame;
import App.Steam;

import java.io.IOException;

public class index
{
    public static void main(String[] args)
    {
        Steam steam = new Steam();

        LogInFrame logInFrame = new LogInFrame();

        while(logInFrame.isVisible())
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Account user = logInFrame.getUser();

        if(user != null)
        {
            if(user instanceof User)
            {
                UserMainFrame userMainFrame = new UserMainFrame((User) user);
            }
            else
            {
                AdminMainFrame adminMainFrame = new AdminMainFrame((Administrator) user);
            }
        }

    }
}