package main.GL;

import main.GL.interfaces.Allergen;
import main.GL.interfaces.Kremkuchenbar;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
/*
Kremkuchenobjekte sind Kuchen aber mit einer Kremsorte
 */
public class Kremkuchen extends Kuchen implements Kremkuchenbar, Serializable
{
    /*
    Instanzvariablen
     */
    private String kremsorte;
    @Serial
    private static final long serialVersionUID = 3L;
    /*
    Default Konstruktor
     */
    public Kremkuchen()
    {
        super();
        kremsorte = "";
    }
    /*
    Vollstaendig Konstruktor
     */
    public Kremkuchen(BigDecimal preis, Date inspektionsdatum, int fachnummer, Hersteller hersteller, int naehrwert, Collection<Allergen> allergene, Duration haltbarkeit, String kremsorte) throws NullPointerException
    {
        super(preis, inspektionsdatum, fachnummer, hersteller, naehrwert, allergene, haltbarkeit);

        if(kremsorte == null)
        {
            throw new NullPointerException("Kremsorte darf nicht null sein.");
        }

        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte()
    {
        return kremsorte;
    }

    public void setKremsorte(String kremsorte)
    {
        this.kremsorte = kremsorte;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - Kremsorte: " + getKremsorte();
    }
}
