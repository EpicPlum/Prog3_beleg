package main.GL.dekorator;

import main.GL.interfaces.Allergen;
import main.GL.interfaces.Herstellerbar;
import main.GL.interfaces.Kuchenbar;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Belag extends BelagDekorator implements Serializable
{
    private String name;
    private BigDecimal preis = new BigDecimal(0);
    private int naehrwert = 0;
    private Collection<Allergen> allergen = new ArrayList<Allergen>();
    private Duration haltbarkeit;
    @Serial
    private static final long serialVersionUID = 11L;

    public Belag(String name, BigDecimal preis, int naehrwert, Duration haltbarkeit, Collection<Allergen> allergen, Kuchenbar newKuchen)
    {
        super(newKuchen);


        if(newKuchen.getName() != null)
            this.name =  name + ", " + tempKuchen.getName();
        this.preis = preis.add(tempKuchen.getPreis());
        System.out.println(preis);
        System.out.println(tempKuchen.getPreis());
        this.naehrwert = naehrwert + tempKuchen.getNaehrwert();
        this.allergen.addAll(tempKuchen.getAllergene());
        this.allergen.addAll(allergen);
        System.out.println(tempKuchen.getHaltbarkeit().toDays());
        this.haltbarkeit = Duration.ofDays(Math.min(haltbarkeit.toDays(), tempKuchen.getHaltbarkeit().toDays()));
    }



    public Herstellerbar getHersteller()
    {
        return tempKuchen.getHersteller();
    }
    public Collection<Allergen> getAllergene()
    {
        return tempKuchen.getAllergene();
    }
    public int getNaehrwert()
    {
        return tempKuchen.getNaehrwert();
    }
    public Duration getHaltbarkeit()
    {
        return tempKuchen.getHaltbarkeit();
    }

    @Override
    public String getName()
    {
        return name;
    }

    public BigDecimal getPreis()
    {
        return tempKuchen.getPreis();
    }

    @Override
    public Date getInspektionsdatum()
    {
        return tempKuchen.getInspektionsdatum();
    }

    @Override
    public int getFachnummer()
    {
        return tempKuchen.getFachnummer();
    }

    @Override
    public void setFachnummer(int fachnummer)
    {
        tempKuchen.setFachnummer(fachnummer);
    }

    @Override
    public String toString()
    {
        if(tempKuchen instanceof Kuchenboden)
        {
            return "Namen: " + name + "Preis: " + preis + " Naehrwert: " + naehrwert + " Allergene: " + allergen + " Haltbarkeit: " + haltbarkeit.toDays() + " Tage --- " + tempKuchen.toString();
        }
        else
            return "Namen: " + name + "Preis: " + preis + " Naehrwert: " + naehrwert + " Allergene: " + allergen + " Haltbarkeit: " + haltbarkeit.toDays() + " Tage --- ";

    }

}
