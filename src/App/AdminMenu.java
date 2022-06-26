package App;

import App.Accounts.Account;
import App.Accounts.User;
import App.Exceptions.BadInputException;
import App.FilesHandler.GamesFile;
import App.FilesHandler.UsersFile;
import App.Products.App;
import App.Products.ESRBClassification;
import App.Products.Game;
import App.Products.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdminMenu
{
    public static void menu(Steam steam)
    {
        Scanner scanner = new Scanner(System.in);

        boolean end = false;
        int option = 0;
        while(!end)
        {
            waitForEnter();
            cls();

            System.out.println("1. Add product        || 2. View Products");
            System.out.println("3. Search product     || 4. Remove product");
            System.out.println("5. Edit product       || 6. View users");
            System.out.println("7. Set wallet user    || 8. Exit");
            System.out.print("Select an option: ");

            boolean badInput = true;
            while(badInput)
            {
                try{
                    option = Integer.parseInt(scanner.nextLine());
                    badInput = false;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Option must be a number");
                }
            }

            waitForEnter();
            cls();

            switch (option)
            {
                case 1:
                    System.out.println("1. Add a game || 2. Add an app");
                    System.out.print("Select an option: ");
                    int option2 = 0;
                    badInput = true;
                    while(badInput)
                    {
                        try{
                            option2 = Integer.parseInt(scanner.nextLine());
                            badInput = false;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Option must be a number");
                        }
                    }

                    if(option2 == 1)
                    {
                        cls();
                        System.out.println("Enter the name of the game:");
                        String name = scanner.nextLine();
                        System.out.println("Enter the price of the game:");
                        badInput = true;
                        float price = 0;
                        while(badInput)
                        {
                            try{
                                price = Float.parseFloat(scanner.nextLine());
                                badInput = false;
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("Price must be a number");
                            }
                        }
                        System.out.println("Enter the ESRB classification of the game:");
                        badInput = true;
                        ESRBClassification ESRB = null;
                        while(badInput)
                        {
                            try{
                                ESRB = ESRBClassification.valueOf(scanner.nextLine());
                                badInput = false;
                            }
                            catch (IllegalArgumentException e)
                            {
                                System.out.println("ESRB classification must be one of the following: " + Arrays.toString(ESRBClassification.values()));
                            }
                        }
                        ArrayList<String> genres = new ArrayList<>();
                        while(true)
                        {
                            System.out.println("Enter genres of the game:");
                            String genre = scanner.nextLine();
                            if(genre.equals("")) break;
                            genres.add(genre);

                            System.out.println("Would you like to add another genre? (y/n)");
                            String answer = scanner.nextLine();
                            if(answer.equals("y")) {
                            }
                            else if(answer.equals("n")) break;
                            else System.out.println("Answer must be y or n");
                        }
                        ArrayList<String> labels = new ArrayList<>();
                        while(true)
                        {
                            System.out.println("Enter labels of the game:");
                            String label = scanner.nextLine();
                            if(label.equals("")) break;
                            labels.add(label);
                            
                            System.out.println("Would you like to add another label? (y/n)");
                            String answer = scanner.nextLine();
                            if(answer.equals("y")) {
                            }
                            else if(answer.equals("n")) break;
                            else System.out.println("Answer must be y or n");
                        }
                        ArrayList<String> platforms = new ArrayList<>();
                        while(true)
                        {
                            System.out.println("Enter platforms of the game:");
                            String platform = scanner.nextLine();
                            if(platform.equals("")) break;
                            platforms.add(platform);
                            
                            System.out.println("Would you like to add another platform? (y/n)");
                            String answer = scanner.nextLine();
                            if(answer.equals("y")) {
                            }
                            else if(answer.equals("n")) break;
                            else System.out.println("Answer must be y or n");
                        }
                        ArrayList<String> lenguages = new ArrayList<>();
                        while (true)
                        {
                            System.out.println("Enter lenguages of the game:");
                            String lenguage = scanner.nextLine();
                            if(lenguage.equals("")) break;
                            lenguages.add(lenguage);
                            
                            System.out.println("Would you like to add another lenguage? (y/n)");
                            String answer = scanner.nextLine();
                            if(answer.equals("y")) {
                            }
                            else if(answer.equals("n")) break;
                            else System.out.println("Answer must be y or n");
                        }
                        System.out.println("Enter the release date of the game: (dd/mm/yyyy)");
                        AppDate releaseDate = new AppDate(scanner.nextLine());
                        System.out.println("Is the game multiplayer? y / n");
                        boolean multiplayer = scanner.nextLine().equals("y");
                        System.out.println("Enter the developer of the game:");
                        String developer = scanner.nextLine();
                        System.out.println("Enter the publisher of the game:");
                        String publisher = scanner.nextLine();
                        System.out.println("Enter the size of the game in GB:");
                        badInput = true;
                        float size = 0;
                        while(badInput)
                        {
                            try{
                                size = Float.parseFloat(scanner.nextLine());
                                badInput = false;
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("Size must be a number");
                            }
                        }

                        boolean success = false;
                        while (!success)
                        {
                            try {
                                steam.createGame(
                                        name,
                                        price,
                                        ESRB,
                                        releaseDate.getYear(), releaseDate.getMonth(), releaseDate.getDay(),
                                        multiplayer,
                                        developer,
                                        publisher,
                                        size,
                                        genres,
                                        labels,
                                        lenguages,
                                        platforms
                                );
                                success = true;
                            } catch (BadInputException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    else if(option2 == 2)
                    {
                        cls();
                        System.out.println("Enter the name of the app:");
                        String name = scanner.nextLine();
                        System.out.println("Enter the price of the app:");
                        badInput = true;
                        float price = 0;
                        while (badInput) {
                            try {
                                price = Float.parseFloat(scanner.nextLine());
                                badInput = false;
                            } catch (NumberFormatException e) {
                                System.out.println("Price must be a number");
                            }
                        }
                        System.out.println("Enter the ESRB classification of the app:");
                        badInput = true;
                        ESRBClassification ESRB = null;
                        while (badInput) {
                            try {
                                ESRB = ESRBClassification.valueOf(scanner.nextLine());
                                badInput = false;
                            } catch (IllegalArgumentException e) {
                                System.out.println("ESRB classification must be one of the following: " + Arrays.toString(ESRBClassification.values()));
                            }
                        }
                        System.out.println("Enter the functionalitie of the app:");
                        String functionalitie = scanner.nextLine();

                        try
                        {
                            steam.createApp(name, price, ESRB, functionalitie);
                        }
                        catch (BadInputException e)
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                    else
                    {
                        System.out.println("Invalid option");
                    }

                    break;
                case 2:
                    for (Product p : steam.getProducts())
                    {
                        System.out.println(p.toString());
                        System.out.println("__________________________________________");
                    }
                    System.out.println("Press enter to continue");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("1. Search by name || 2. Search by genre");
                    System.out.println("3. Search by id   || 4. Search by rating");
                    badInput = true;
                    int option3 = 0;
                    while (badInput)
                    {
                        try
                        {
                            option3 = Integer.parseInt(scanner.nextLine());
                            badInput = false;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid option");
                        }
                    }

                    switch (option3)
                    {
                        case 1:
                            System.out.println("Enter the name of the product:");
                            String name = scanner.nextLine();
                            Product product = steam.getThroughName(name);
                            if (product == null)
                            {
                                System.out.println("Product not found");
                            }
                            else
                            {
                                System.out.println(product.toString());
                            }
                            break;
                        case 2:
                            System.out.println("Enter the genre to search:");
                            String genre = scanner.nextLine();
                            ArrayList<Product> products = steam.getByGenre(genre);
                            if (products.isEmpty())
                            {
                                System.out.println("No products found");
                            }
                            else
                            {
                                for (Product p : products)
                                {
                                    System.out.println(p.toString());
                                    System.out.println("__________________________________________");
                                }
                            }
                            break;
                        case 3:
                            badInput = true;
                            int id = 0;
                            while (badInput)
                            {
                                System.out.println("Enter the id of the product:");
                                try
                                {
                                    id = Integer.parseInt(scanner.nextLine());
                                    badInput = false;
                                }
                                catch (NumberFormatException e)
                                {
                                    System.out.println("Invalid option");
                                }
                            }
                            Product product2 = steam.getProductById(id);
                            if (product2 == null)
                            {
                                System.out.println("Product not found");
                            }
                            else
                            {
                                System.out.println(product2.toString());
                            }
                            break;
                        case 4:
                            System.out.println("Enter the rating to search:");
                            badInput = true;
                            int rating = 0;
                            while (badInput)
                            {
                                try
                                {
                                    rating = Integer.parseInt(scanner.nextLine());
                                    badInput = false;
                                }
                                catch (NumberFormatException e)
                                {
                                    System.out.println("Invalid option");
                                }
                            }
                            ArrayList<Product> products2 = steam.getByRating(rating);
                            if (products2.isEmpty())
                            {
                                System.out.println("No products found");
                            }
                            else
                            {
                                for (Product p : products2)
                                {
                                    System.out.println(p.toString());
                                    System.out.println("__________________________________________");
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                    break;
                case 4:
                    badInput = true;
                    int id = 0;
                    while (badInput)
                    {
                        System.out.println("Enter the id of the product to delete:");
                        try
                        {
                            id = Integer.parseInt(scanner.nextLine());
                            badInput = false;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid option");
                        }
                    }
                    steam.removeThroughId(id);
                    break;
                case 5:
                    System.out.println("Enter the id of the product to update:");
                    badInput = true;
                    id = 0;
                    while (badInput)
                    {
                        try
                        {
                            id = Integer.parseInt(scanner.nextLine());
                            badInput = false;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid option");
                        }
                    }
                    Product p = steam.getProductById(id);

                    if (p == null)
                    {
                        System.out.println("Product not found");
                        break;
                    }
                    cls();
                    if(p instanceof App)
                    {
                        System.out.println("1. Update name                || 2. Update price");
                        System.out.println("3. Update ESRB classification || 4. Update functionalitie");
                        badInput = true;
                        int option4 = 0;
                        while (badInput)
                        {
                            try
                            {
                                option4 = Integer.parseInt(scanner.nextLine());
                                badInput = false;
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("Invalid option");
                            }
                        }
                        switch (option4) {
                            case 1 -> {
                                System.out.println("Enter the new name:");
                                String name = scanner.nextLine();
                                p.setName(name);
                                GamesFile.modifyProduct(p);
                            }
                            case 2 -> {
                                System.out.println("Enter the new price:");
                                double price = Double.parseDouble(scanner.nextLine());
                                ((App) p).setPrice((float) price);
                                GamesFile.modifyProduct(p);
                            }
                            case 3 -> {
                                System.out.println("Enter the new ESRB classification:");
                                String ESRB = scanner.nextLine();
                                badInput = true;
                                while (badInput) {
                                    try {
                                        p.setESRB(ESRBClassification.valueOf(ESRB));
                                        badInput = false;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Invalid option");
                                    }
                                }
                                GamesFile.modifyProduct(p);
                            }
                            case 4 -> {
                                System.out.println("Enter the new functionalitie:");
                                String functionalitie = scanner.nextLine();
                                ((App) p).setFunction(functionalitie);
                                GamesFile.modifyProduct(p);
                            }
                            default -> System.out.println("Invalid option");
                        }
                    }
                    else if(p instanceof Game)
                    {
                        System.out.println("1. Update name                || 2. Update price");
                        System.out.println("3. Update ESRB classification || 4. Update size");
                        badInput = true;
                        int option4 = 0;
                        while (badInput)
                        {
                            try
                            {
                                option4 = Integer.parseInt(scanner.nextLine());
                                badInput = false;
                            }
                            catch (NumberFormatException e)
                            {
                                System.out.println("Invalid option");
                            }
                        }
                        switch (option4) {
                            case 1 -> {
                                System.out.println("Enter the new name:");
                                String name = scanner.nextLine();
                                p.setName(name);
                                GamesFile.modifyProduct(p);
                            }
                            case 2 -> {
                                System.out.println("Enter the new price:");
                                double price = Double.parseDouble(scanner.nextLine());
                                p.setPrice((float) price);
                                GamesFile.modifyProduct(p);
                            }
                            case 3 -> {
                                System.out.println("Enter the new ESRB classification:");
                                String ESRB = scanner.nextLine();
                                badInput = true;
                                while (badInput) {
                                    try {
                                        p.setESRB(ESRBClassification.valueOf(ESRB));
                                        badInput = false;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Invalid option");
                                    }
                                }
                                GamesFile.modifyProduct(p);
                            }
                            case 4 -> {
                                System.out.println("Enter the new size:");
                                badInput = true;
                                float size = 0;
                                while (badInput) {
                                    try {
                                        size = Float.parseFloat(scanner.nextLine());
                                        badInput = false;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid option");
                                    }
                                }
                                ((Game) p).setSizeGB(size);
                                GamesFile.modifyProduct(p);
                            }
                            default -> System.out.println("Invalid option");
                        }
                        break;
                    }
                case 6:
                    for (Account a : steam.getAllUsers())
                    {
                        System.out.println(a.toString());
                        System.out.println("__________________________________________");
                    }
                    waitForEnter();
                    break;
                case 7:
                    badInput = true;
                    System.out.println("Enter the mail of the user to modify:");
                    String mail = scanner.nextLine();
                    UsersFile uf = new UsersFile();
                    User user = null;
                    try {
                        user = uf.userFindMail(mail);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(user == null)
                    {
                        System.out.println("User not found");
                        break;
                    }
                    System.out.println("Enter how much money to add:");
                    badInput = true;
                    double money = 0;
                    while (badInput)
                    {
                        try
                        {
                            money = Double.parseDouble(scanner.nextLine());
                            badInput = false;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Invalid option");
                        }
                    }
                    user.addFounds((float) money);
                    try {
                        uf.userUpdate(user);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    end = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
    }

    public static void cls()
    {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public static void waitForEnter()
    {
        System.out.println("Press ENTER to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
