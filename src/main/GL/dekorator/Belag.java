package main.GL.dekorator;

import main.GL.*;
import main.GL.interfaces.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Belag extends BelagDekorator implements Serializable
{
    private String name;
    private BigDecimal preis = new BigDecimal(0);
    private int naehrwert = 0;
    private Collection<Allergen> allergen = new HashSet<>();
    private Duration haltbarkeit;
    private Kuchenbar boden;
    @Serial
    private static final long serialVersionUID = 10L;

    public Belag()
    {
        name = "";
        preis = new BigDecimal(0);
        naehrwert = 0;
        allergen = new HashSet<>();
        haltbarkeit = Duration.ofDays(0);
    }

    public Belag(String name, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, Kuchenbar boden, Kuchenbar newKuchen)
    {
        super(newKuchen);

        this.boden = boden;

        if(newKuchen.getName() != null)
            this.name =  name + ", " + tempKuchen.getName();
        this.preis = preis.add(tempKuchen.getPreis());
        this.naehrwert = naehrwert + tempKuchen.getNaehrwert();
        this.allergen.addAll(tempKuchen.getAllergene());
        this.allergen.addAll(allergen);
        this.haltbarkeit = Duration.ofDays(Math.min(haltbarkeit.toDays(), tempKuchen.getHaltbarkeit().toDays()));
    }

    public Collection<Allergen> getAllergene()
    {
        return allergen;
    }
    public int getNaehrwert()
    {
        return  naehrwert;
    }
    public Duration getHaltbarkeit()
    {
        return haltbarkeit;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public Hersteller getHersteller()
    {
        return super.getHersteller();
    }

    @Override
    public void setHersteller(Hersteller hersteller)
    {
        super.setHersteller(hersteller);
    }

    @Override
    public void setHaltbarkeit(Duration haltbarkeit)
    {
        this.haltbarkeit = haltbarkeit;
    }

    public BigDecimal getPreis()
    {
        return preis;
    }

    @Override
    public Date getInspektionsdatum()
    {
        return super.getInspektionsdatum();
    }

    @Override
    public int getFachnummer()
    {
        return super.getFachnummer();
    }

    @Override
    public void setFachnummer(int fachnummer)
    {
        super.setFachnummer(fachnummer);
    }

    @Override
    public void setInspektionsdatum(Date inspektionsdatum)
    {
        super.setInspektionsdatum(inspektionsdatum);
    }

    @Override
    public String toString()
    {
        return "Belaege: " + name + "Preis: " + preis + " Naehrwert: " + naehrwert + " Allergene: " + allergen + " Haltbarkeit: " +
                haltbarkeit.toDays() + " Tage - " + "Inspektionsdatum: " + getInspektionsdatum()
                + " - Fachnummer: " + getFachnummer() + " - " + "Hersteller: " + getHersteller().getName() + boden.belagToString();
    }

    @Override
    public String belagToString()
    {
        return "";
    }
}
