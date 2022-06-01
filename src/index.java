import App.*;
import App.Accounts.Administrator;
import App.Accounts.Adress;
import App.Accounts.User;

import java.util.UUID;

public class index
{
    public static void main(String[] args)
    {
        User user = new User(
                "a@gmail.com",
                "123",
                "Juan",
                "Perez",
                "123456789",
                "123456789",
                new Adress("Argentina", "Buenos Aires", "Ciudad Autonoma de Buenos Aires", "Av. Siempre Viva 123"),
                UUID.randomUUID(),
                new AppDate(27, 1, 2003)
        );

        System.out.println(user);
    }
}
