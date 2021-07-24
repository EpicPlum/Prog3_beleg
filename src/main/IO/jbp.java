package main.IO;


import main.GL.Automat;
import main.GL.Node;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class jbp
{
    public static void saveAutomat(XMLEncoder encoder, Automat automat) throws IOException
    {
        encoder.setPersistenceDelegate(Node.class, new DefaultPersistenceDelegate(new String[]{"data", "next"}));
        encoder.writeObject(automat);
    }

    public static void saveAutomat(String fileName, Automat automat)
    {

        try(XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));)
        {
            saveAutomat(encoder, automat);
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

    public static Automat loadAutomat(XMLDecoder decoder) throws IOException, ClassNotFoundException
    {
        return (Automat)decoder.readObject();
    }

    public static Automat loadAutomat(String fileName)
    {

        try(XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));)
        {
            return loadAutomat(decoder);
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
