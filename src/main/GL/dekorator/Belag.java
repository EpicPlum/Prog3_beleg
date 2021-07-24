package main.GL.dekorator;

import main.GL.*;
import main.GL.interfaces.Allergen;
import main.GL.interfaces.Kuchenbar;

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
    private Collection<Allergen> allergen = new HashSet<Allergen>();
    private Duration haltbarkeit;
    @Serial
    private static final long serialVersionUID = 11L;

    public Belag(String name, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, Kuchenbar newKuchen)
    {
        super(newKuchen);

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
        return "Namen: " + name + "Preis: " + preis + " Naehrwert: " + naehrwert + " Allergene: " + allergen + " Haltbarkeit: " + haltbarkeit.toDays() + " Tage - " + tempKuchen.belagToString();
    }

    @Override
    public String belagToString()
    {
        if(tempKuchen instanceof Kuchen)
        {
            return "Inspektionsdatum: " + getInspektionsdatum() + " - Fachnummer: " + getFachnummer() + " - Kuchentyp: Kuchen - " + "Hersteller: " + getHersteller().getName();
        }
        else if(tempKuchen instanceof Kremkuchen)
        {
            return "Inspektionsdatum: " + getInspektionsdatum() + " - Fachnummer: " + getFachnummer() + " - Kuchentyp: Kremkuchen - " + "Hersteller: " + getHersteller().getName();
        }
        else if(tempKuchen instanceof Obstkuchen)
        {
            return "Inspektionsdatum: " + getInspektionsdatum() + " - Fachnummer: " + getFachnummer() + " - Kuchentyp: Obstkuchen - " + "Hersteller: " + getHersteller().getName();
        }
        else if(tempKuchen instanceof Obsttorte)
        {
            return "Inspektionsdatum: " + getInspektionsdatum() + " - Fachnummer: " + getFachnummer() + " - Kuchentyp: Obsttorte - " + "Hersteller: " + getHersteller().getName();
        }
        else
            return "";
    }
}
