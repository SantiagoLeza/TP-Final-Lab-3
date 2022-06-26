package App.FilesHandler;

import App.Accounts.Administrator;
import App.Exceptions.MissMatchClassException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
            Administrator admin = (Administrator) content;

            dos.writeUTF(admin.getUuid().toString());
            dos.writeUTF(admin.getMail());
            dos.writeUTF(admin.getPassword());

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


    public Administrator adminFindMail(String mail) throws IOException
    {
        FileInputStream fis = new FileInputStream("./src/App/FilesHandler/admin.bin");
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

    public void adminUpdate ( Administrator admin ) throws IOException
        {
            ArrayList<Administrator> admins = readBinary("./src/App/FilesHandler/admin.bin");

            FileInputStream fis = new FileInputStream("./src/App/FilesHandler/admin.bin");
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


                    if (uuid.toString().equals(admin.getUuid().toString()))
                    {
                        flag = true;
                        for (Administrator a : admins)
                        {
                            if (a.getUuid().toString().equals(admin.getUuid().toString()))
                            {
                                admins.remove(a);
                                admins.add(admin);
                                break;
                            }
                        }

                        dis.close();
                        deleteBinary("./src/App/FilesHandler/admin.bin");

                        for (Administrator u : admins) {
                            writeBinary("./src/App/FilesHandler/admin.bin", u);
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

    public Administrator administratorFindUUID (UUID UUID1) throws IOException
    {
        FileInputStream fis = new FileInputStream("./src/App/FilesHandler/admin.bin");
        DataInputStream dis = new DataInputStream(fis);

        try {

            while(true)
            {
                UUID uuid;
                String mail;
                String password;

                while(true)
                {
                    uuid = UUID.fromString(dis.readUTF());
                    mail = dis.readUTF();
                    password = dis.readUTF();


                    if (uuid.equals(UUID1))
                    {
                        return new Administrator(mail, password, uuid);
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

 }