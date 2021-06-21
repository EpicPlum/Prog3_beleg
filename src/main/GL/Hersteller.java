package main.GL;

import main.GL.interfaces.Herstellerbar;

import java.io.Serializable;

/*
Herstellerobjekte, die sind eine Attribute von Kuchen
 */
public class Hersteller implements Herstellerbar, Comparable<Hersteller>, Serializable
{
    /*
    Instanzvariablen
     */
    private String name;
    private int countKuchen;
    /*
    Default Konstruktor
     */
    public Hersteller()
    {
        name = "";
        countKuchen = 0;
    }
    /*
    Vollstaendig Konstruktor
     */
    public Hersteller(String name)
    {
        this.name = name;
        countKuchen = 0;
    }

    @Override
    public String getName()
    {
        return name;
    }
    public int countKuchen()
    {
        return countKuchen;
    }
    public void incrementCountKuchen(int amount)
    {
        countKuchen = countKuchen + amount;
    }
    public void decrementCountKuchen(int amount)
    {
        countKuchen = countKuchen - amount;
    }

    @Override
    public int compareTo(Hersteller h)
    {
        return (this.getName()).compareTo(h.getName());
    }
}
