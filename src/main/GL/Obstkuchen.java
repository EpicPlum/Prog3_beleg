package main.GL;

import main.GL.interfaces.Allergen;
import main.GL.interfaces.Obstkuchenbar;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
/*
Obstkuchenobjekte sind Kuchen mit einer Obstsorte
 */
public class Obstkuchen extends Kuchen implements Obstkuchenbar, Serializable
{
    /*
    Instanzvariablen
     */
    private String obstsorte;
    @Serial
    private static final long serialVersionUID = 5L;

    public Obstkuchen()
    {
        obstsorte = "";
    }
    /*
    Vollstaendig Konstruktor
     */
    public Obstkuchen(BigDecimal preis, Date inspektionsdatum, Hersteller hersteller, int naehrwert, Collection<Allergen> allergene, Duration haltbarkeit, String obstsorte) throws NullPointerException
    {
        super(preis, inspektionsdatum, hersteller, naehrwert, allergene, haltbarkeit);

        if(obstsorte == null)
        {
            throw new NullPointerException("Obstsorte darf nicht null sein.");
        }
        this.obstsorte = obstsorte;
    }

    @Override
    public String getObstsorte()
    {
        return obstsorte;
    }

    public void setObstsorte(String obstsorte)
    {
        this.obstsorte = obstsorte;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - Obstsorte: " + getObstsorte();
    }
}
