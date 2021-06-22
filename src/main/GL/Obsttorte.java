package main.GL;

import main.GL.interfaces.Allergen;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Obsttorte extends Obstkuchen implements main.GL.interfaces.Obsttortebar, Serializable
{
    String kremsorte;
    @Serial
    private static final long serialVersionUID = 6L;

    public Obsttorte()
    {
        super();
    }

    public Obsttorte(BigDecimal preis, Date inspektionsdatum, int fachnummer, Hersteller hersteller, int naehrwert, Collection<Allergen> allergene, Duration haltbarkeit, String obstsorte, String kremsorte)
    {
        super(preis, inspektionsdatum, fachnummer, hersteller, naehrwert, allergene, haltbarkeit, obstsorte);
        this.kremsorte = kremsorte;
    }

    @Override
    public String getKremsorte()
    {
        return this.kremsorte;
    }
    public void setKremsorte(String kremsorte)
    {
        this.kremsorte = kremsorte;
    }

    public String toString()
    {
        return super.toString() + " - Kremsorte: " + getKremsorte();
    }


}
