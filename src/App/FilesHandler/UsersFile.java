package App.FilesHandler;

import App.Accounts.Adress;
import App.Accounts.User;
import App.AppDate;
import App.Exceptions.MissMatchClassException;
import App.Products.Product;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

public class UsersFile implements BinaryFiles
{
    @Override
    public void writeBinary(String fileName, Object content) throws IOException, MissMatchClassException
    {
        FileOutputStream fos = new FileOutputStream(fileName, true);
        DataOutputStream dos = new DataOutputStream(fos);
        try
        {
            User user = (User) content;

            dos.writeUTF(user.getUuid().toString());
            dos.writeUTF(user.getMail());
            dos.writeUTF(user.getPassword());
            dos.writeUTF(user.getName());
            dos.writeUTF(user.getSurname());
            dos.writeUTF(user.getID());
            dos.writeUTF(user.getPhone());
            dos.writeUTF(user.getAdress().getCity());
            dos.writeUTF(user.getAdress().getStreet());
            dos.writeUTF(user.getAdress().getCountry());
            dos.writeUTF(user.getAdress().getState());
            dos.writeInt(user.getBirthDate().getDay());
            dos.writeInt(user.getBirthDate().getMonth());
            dos.writeInt(user.getBirthDate().getYear());
            dos.writeFloat(user.getWallet());
            
            dos.close();
        }
        catch (ClassCastException e)
        {
            throw new MissMatchClassException("Not an Administrator object");
        }
    }

    @Override
    public void deleteBinary(String fileName) throws IOException
    {
        if (Files.exists(Path.of(fileName)))
        {
            Files.delete(Path.of(fileName));
            Files.createFile(Path.of(fileName));
        }
    }

    @Override
    public ArrayList<User> readBinary(String fileName) throws IOException
    {
        ArrayList<User> users = new ArrayList<User>();

        FileInputStream fis = new FileInputStream(fileName);
        DataInputStream dis = new DataInputStream(fis);

        UUID uuid;
        String mail;
        String password;
        String name;
        String surname;
        String ID;
        String phone;
        String city;
        String street;
        String country;
        String state;
        Adress adress;
        int day;
        int month;
        int year;
        AppDate birthDate;
        float wallet;
        ArrayList<Integer> cart;
        ArrayList<Integer> wishList;
        ArrayList <Integer> library;

        try {

            while(true)
            {
                uuid = UUID.fromString(dis.readUTF());
                mail = dis.readUTF();
                password = dis.readUTF();
                name = dis.readUTF();
                surname =  dis.readUTF();
                ID = dis.readUTF();
                phone = dis.readUTF();
                city = dis.readUTF();
                street = dis.readUTF();
                country = dis.readUTF();
                state = dis.readUTF();
                adress = new Adress(city, street, country, state);
                day =  dis.readInt();
                month = dis.readInt();
                year =  dis.readInt();
                birthDate = new AppDate(day, month, year);
                wallet = dis.readFloat();
                cart = UserGamesFile.getCart(uuid);
                wishList = UserGamesFile.getWishList(uuid);
                library = UserGamesFile.getLibrary(uuid);

                users.add(new User(mail, password, name, surname, ID, phone, adress, uuid, birthDate, wallet, wishList, cart, library));
            }
        }
        catch (EOFException e)
        {}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            dis.close();
        }

        return users;
    }


    public User userFindMail(String mail) throws IOException
    {
        FileInputStream fis = new FileInputStream("./src/App/FilesHandler/users.bin");
        DataInputStream dis = new DataInputStream(fis);

        try {

            String mail2;
            String password;
            String name;
            String surname;
            String ID;
            String phone;
            String city;
            String street;
            String country;
            String state;
            int day;
            int month;
            int year;
            float wallet;
            ArrayList<Integer> cart;
            ArrayList<Integer> wishList;
            ArrayList <Integer> library;
            
            while(true)
            {
                UUID uuid = UUID.fromString(dis.readUTF());
                mail2 = dis.readUTF();
                password = dis.readUTF();
                name = dis.readUTF();
                surname =  dis.readUTF();
                ID = dis.readUTF();
                phone = dis.readUTF();
                city = dis.readUTF();
                street = dis.readUTF();
                country = dis.readUTF();
                state = dis.readUTF();
                day =  dis.readInt();
                month = dis.readInt();
                year =  dis.readInt();
                wallet = dis.readFloat();

                cart = UserGamesFile.getCart(uuid);
                wishList = UserGamesFile.getWishList(uuid);
                library = UserGamesFile.getLibrary(uuid);

                if (mail2.equals(mail))
                {
                    return new User(mail, password, name, surname, ID, phone, new Adress(country, state, city, street), uuid, new AppDate(day, month, year), wallet, wishList, cart, library);
                }
                
            }
        }
        catch (EOFException e)
        {}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            dis.close();
        }

        return null;
    }
    
    public User userFindPhone(String number) throws IOException
        {
            FileInputStream fis = new FileInputStream("./src/App/FilesHandler/users.bin");
            DataInputStream dis = new DataInputStream(fis);
    
            try {

                UUID uuid;
                String mail;
                String password;
                String name;
                String surname;
                String ID;
                String phone;
                String city;
                String street;
                String country;
                String state;
                int day;
                int month;
                int year;
                float wallet;

                ArrayList<Integer> cart;
                ArrayList<Integer> wishList;
                ArrayList<Integer> library;
                
                while(true)
                {
                    uuid = UUID.fromString(dis.readUTF());
                    mail = dis.readUTF();
                    password = dis.readUTF();
                    name = dis.readUTF();
                    surname = dis.readUTF();
                    ID = dis.readUTF();
                    phone = dis.readUTF();
                    city = dis.readUTF();
                    street = dis.readUTF();
                    country = dis.readUTF();
                    state = dis.readUTF();
                    day = dis.readInt();
                    month = dis.readInt();
                    year = dis.readInt();
                    wallet = dis.readFloat();

                    cart = UserGamesFile.getCart(uuid);
                    wishList = UserGamesFile.getWishList(uuid);
                    library = UserGamesFile.getLibrary(uuid);
                    
                    if (phone.equals(number))
                    {
                        return new User(mail, password, name, surname, ID, phone, new Adress(country, state, city, street), uuid, new AppDate(day, month, year), wallet, wishList, cart, library);
                    }
                }
            }
            catch (EOFException e)
            {}
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                dis.close();
            }
    
            return null;
        }
        
        public User userFindUUID (UUID UUID1) throws IOException
        {
            FileInputStream fis = new FileInputStream("./src/App/FilesHandler/users.bin");
            DataInputStream dis = new DataInputStream(fis);
    
            try {
                
                while(true)
                {
                    UUID uuid;
                    String mail;
                    String password;
                    String name;
                    String surname;
                    String ID;
                    String phone;
                    String city;
                    String street;
                    String country;
                    String state;
                    int day;
                    int month;
                    int year;
                    float wallet;

                    ArrayList<Integer> cart;
                    ArrayList<Integer> wishList;
                    ArrayList<Integer> library;

                    while(true)
                    {
                        uuid = UUID.fromString(dis.readUTF());
                        mail = dis.readUTF();
                        password = dis.readUTF();
                        name = dis.readUTF();
                        surname = dis.readUTF();
                        ID = dis.readUTF();
                        phone = dis.readUTF();
                        city = dis.readUTF();
                        street = dis.readUTF();
                        country = dis.readUTF();
                        state = dis.readUTF();
                        day = dis.readInt();
                        month = dis.readInt();
                        year = dis.readInt();
                        wallet = dis.readFloat();

                        cart = UserGamesFile.getCart(uuid);
                        wishList = UserGamesFile.getWishList(uuid);
                        library = UserGamesFile.getLibrary(uuid);

                        if (uuid.equals(UUID1))
                        {
                            return new User(mail, password, name, surname, ID, phone, new Adress(country, state, city, street), uuid, new AppDate(day, month, year), wallet, wishList, cart, library);
                        }
                    }
                }
            }
            catch (EOFException e)
            {}
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                dis.close();
            }
    
            return null;
        }

    public User userFindID(String id) throws IOException
    {
        FileInputStream fis = new FileInputStream("./src/App/FilesHandler/users.bin");
        DataInputStream dis = new DataInputStream(fis);

        try {

            while(true)
            {
                UUID uuid;
                String mail;
                String password;
                String name;
                String surname;
                String ID;
                String phone;
                String city;
                String street;
                String country;
                String state;
                int day;
                int month;
                int year;
                float wallet;

                ArrayList<Integer> cart;
                ArrayList<Integer> wishList;
                ArrayList<Integer> library;


                while(true)
                {
                    uuid = UUID.fromString(dis.readUTF());
                    mail = dis.readUTF();
                    password = dis.readUTF();
                    name = dis.readUTF();
                    surname = dis.readUTF();
                    ID = dis.readUTF();
                    phone = dis.readUTF();
                    city = dis.readUTF();
                    street = dis.readUTF();
                    country = dis.readUTF();
                    state = dis.readUTF();
                    day = dis.readInt();
                    month = dis.readInt();
                    year = dis.readInt();
                    wallet = dis.readFloat();

                    cart = UserGamesFile.getCart(uuid);
                    wishList = UserGamesFile.getWishList(uuid);
                    library = UserGamesFile.getLibrary(uuid);

                    if (ID.equals(id))
                    {
                        return new User(mail, password, name, surname, ID, phone, new Adress(country, state, city, street), uuid, new AppDate(day, month, year), wallet, wishList, cart, library);
                    }
                }
            }
        }
        catch (EOFException e)
        {}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            dis.close();
        }

        return null;
    }

    public void userUpdate (User user) throws IOException
    {
        ArrayList<User> users = readBinary("./src/App/FilesHandler/users.bin");

        FileInputStream fis = new FileInputStream("./src/App/FilesHandler/users.bin");
        DataInputStream dis = new DataInputStream(fis);

        try
        {
            UUID uuid = null;
            boolean flag = false;

            while (true) {
                if (flag) break;
                uuid = UUID.fromString(dis.readUTF());
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readUTF();
                dis.readInt();
                dis.readInt();
                dis.readInt();
                dis.readFloat();

                if (uuid.toString().equals(user.getUuid().toString())) {
                    flag = true;
                    for (User u : users) {
                        if (u.getUuid().toString().equals(user.getUuid().toString())) {
                            users.remove(u);
                            users.add(user);
                            break;
                        }
                    }

                    dis.close();
                    deleteBinary("./src/App/FilesHandler/users.bin");

                    for (User u : users) {
                        writeBinary("./src/App/FilesHandler/users.bin", u);
                    }
                }
            }
        }
        catch (EOFException e)
        {

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        } finally {
            dis.close();
        }


    }


    
    
}
