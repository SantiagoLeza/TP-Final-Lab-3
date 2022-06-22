package App.FilesHandler;

import App.Accounts.Administrator;
import App.Accounts.Adress;
import App.Accounts.User;
import App.AppDate;
import App.Exceptions.MissMatchClassException;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class AdminFile implements BinaryFiles
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

            dos.close();
        }
        catch (ClassCastException e)
        {
            throw new MissMatchClassException("Not an Administrator object");
        }
    }

    @Override
    public ArrayList<Administrator> readBinary(String fileName) throws IOException
    {
        ArrayList<Administrator> admins = new ArrayList<Administrator>();

        FileInputStream fis = new FileInputStream(fileName);
        DataInputStream dis = new DataInputStream(fis);

        UUID uuid;
        String mail;
        String password;

        try {

            while(true)
            {
                uuid = UUID.fromString(dis.readUTF());
                mail = dis.readUTF();
                password = dis.readUTF();

                admins.add(new Administrator(mail, password, uuid));
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

        return admins;
    }


    public Administrator userFindMail(String mail) throws IOException
    {
        FileInputStream fis = new FileInputStream("./src/App/FilesHandler/users.bin");
        DataInputStream dis = new DataInputStream(fis);

        try {

            UUID uuid;
            String mail2;
            String password;

            while(true)
            {
                uuid = UUID.fromString(dis.readUTF());
                mail2 = dis.readUTF();
                password = dis.readUTF();
                if (mail2.equals(mail))
                {
                    return new Administrator(mail, password, uuid);
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

 }