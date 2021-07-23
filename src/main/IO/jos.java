package main.IO;

import main.GL.Automat;

import java.io.*;

public class jos
{
    public static void saveAutomat(ObjectOutput objectOutput, Automat automat) throws IOException
    {
        objectOutput.writeObject(automat);
    }

    public static boolean saveAutomat(String fileName, Automat automat) throws NullPointerException
    {
        //File out = new File(System.getProperty("user.home"), fileName);
        if(fileName == null || automat == null)
        {
            throw new NullPointerException("Filename/Automat ist null.");
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            saveAutomat(oos, automat);
            return true;
        } catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static Automat loadAutomat(ObjectInput objectInput) throws IOException, ClassNotFoundException
    {
        return (Automat)objectInput.readObject();
    }

    public static Automat loadAutomat(String fileName)
    {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            return loadAutomat(ois);
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
