package main.GL.dekorator;

import main.GL.Hersteller;
import main.GL.Verkaufsobjekt;
import main.GL.interfaces.Allergen;
import main.GL.interfaces.Kuchenbar;
import main.GL.interfaces.Verkaufsobjektbar;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Kuchenboden extends Verkaufsobjekt implements Verkaufsobjektbar, Kuchenbar, Serializable
{
    private Hersteller hersteller;
    private int naehrwert;
    private Collection<Allergen> allergene;
    private Duration haltbarkeit;
    @Serial
    private static final long serialVersionUID = 9L;

    public Kuchenboden()
    {
        super();
        hersteller = new Hersteller("");
        naehrwert = 0;
        allergene = new HashSet<Allergen>();
        haltbarkeit = Duration.ofDays(0);
    }

    public Kuchenboden(BigDecimal preis, Date inspektionsdatum, Hersteller hersteller, int naehrwert, Collection<Allergen> allergene, Duration haltbarkeit) throws NullPointerException
    {
        super(preis, inspektionsdatum);

        if(hersteller == null || allergene == null || haltbarkeit == null)
        {
            throw new NullPointerException("Werte darf nicht null sein.");
        }

        this.hersteller = hersteller;
        this.naehrwert = naehrwert;
        this.allergene = allergene;
        this.haltbarkeit = haltbarkeit;
    }

    @Override
    public Hersteller getHersteller()
    {
        return hersteller;
    }

    public void setHersteller(Hersteller hersteller)
    {
        this.hersteller = hersteller;
    }

    @Override
    public Collection<Allergen> getAllergene()
    {
        return allergene;
    }

    public void setAllergene(Collection<Allergen> allergene)
    {
        this.allergene = allergene;
    }

    @Override
    public int getNaehrwert()
    {
        return naehrwert;
    }

    public void setNaehrwert(int naehrwert)
    {
        this.naehrwert = naehrwert;
    }

    @Override
    public Duration getHaltbarkeit()
    {
        return haltbarkeit;
    }

    @Override
    public String getName()
    {
        return "";
    }

    public void setHaltbarkeit(Duration haltbarkeit)
    {
        this.haltbarkeit = haltbarkeit;
    }

    @Override
    public String toString()
    {
        return super.toString() + " - Hersteller: " + hersteller.getName() + " - Allergene: " + allergene + " - Naehrwert: " + naehrwert + " - Haltbarkeit: " + haltbarkeit.toDays() + " Tage";
    }

    public String belagToString()
    {
        return super.toString() + " Kuchen --- " + "Hersteller: " + hersteller.getName();
    }

}
