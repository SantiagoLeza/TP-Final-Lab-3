import App.*;
import App.Accounts.Administrator;
import App.Accounts.Adress;
import App.Accounts.User;

import java.util.TreeSet;
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

        User user2 = new User(
                "b",
                "321",
                "Jorge",
                "Alvarez",
                "654",
                "555",
                new Adress("EEUU", "Florida", "Miami", "Ocean View"),
                UUID.randomUUID(),
                new AppDate(15, 9, 2002)
        );

        TreeSet<User> users = new TreeSet<>();

        users.add(user);
        users.add(user2);

        System.out.println(users);
    }
}
