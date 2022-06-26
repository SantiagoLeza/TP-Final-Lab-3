import App.Accounts.Account;
import App.Accounts.Administrator;
import App.Accounts.User;
import App.AdminMenu;
import App.Frames.LogInFrame;
import App.Frames.UserMainFrame;
import App.Steam;

public class index
{
    public static void main(String[] args)
    {
        Steam steam = new Steam();

        boolean close = false;
        while (!close)
        {
            LogInFrame logInFrame = new LogInFrame();

            while(logInFrame.isVisible())
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                    
                }
            }

            Account user = logInFrame.getUser();


            if(user != null)
            {
                if(user instanceof User)
                {
                    UserMainFrame userMainFrame = new UserMainFrame((User) user, steam);
                    while(userMainFrame.isVisible())
                    {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    close = userMainFrame.isClose();
                }
                else if (user instanceof Administrator)
                {
                    AdminMenu.menu(steam);
                    close = true;
                }
            }
        }

    }
}