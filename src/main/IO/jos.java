package main.IO;

import main.GL.Automat;

import java.io.*;

public class jos
{
    public static void saveAutomat(ObjectOutput objectOutput, Automat automat) throws IOException
    {
        objectOutput.writeObject(automat);
    }

    public static void saveAutomat(String fileName, Automat automat)
    {
        //File out = new File(System.getProperty("user.home"), fileName);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            saveAutomat(oos, automat);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Automat loadAutomat(ObjectInput objectInput) throws IOException, ClassNotFoundException
    {
        return (Automat)objectInput.readObject();
    }

    public static Automat loadAutomat(String fileName)
    {
        //File in = new File(System.getProperty("user.home"), fileName);

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            return loadAutomat(ois);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
