package main.GL;

import main.GL.interfaces.Allergen;
import main.GL.interfaces.Kuchenbar;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
/*
Kuchen im Automat, erben von Verkaufsobjekt
 */
public class Kuchen extends Verkaufsobjekt implements Kuchenbar
{
    /*
    Instanzvariablen
     */
    private Hersteller hersteller;
    private int naehrwert;
    private Collection<Allergen> allergene;
    private Duration haltbarkeit;
    /*
    Default Konstruktor
     */
    public Kuchen()
    {
        super();
        hersteller = new Hersteller();
        naehrwert = 0;
        allergene = new ArrayList<Allergen>();
        haltbarkeit = Duration.ofDays(0);
    }
    /*
    Vollstaendig Konstruktor
     */
    public Kuchen(BigDecimal preis, Date inspektionsdatum, int fachnummer, Hersteller hersteller, int naehrwert, Collection<Allergen> allergene, Duration haltbarkeit) throws NullPointerException
    {
        super(preis, inspektionsdatum, fachnummer);

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

    public void setHaltbarkeit(Duration haltbarkeit)
    {
        this.haltbarkeit = haltbarkeit;
    }


    @Override
    public String toString()
    {
        return super.toString() + " - Hersteller: " + getHersteller().getName() + " - Allergene: " + getAllergene() + " - Naehrwert: " + getNaehrwert() + " - Haltbarkeit: " + getHaltbarkeit().toDays() + " Tage";
    }


}
