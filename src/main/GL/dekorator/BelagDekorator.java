package main.GL.dekorator;

import main.GL.Hersteller;
import main.GL.interfaces.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public abstract class BelagDekorator implements Verkaufsobjektbar, Kuchenbar, Kremkuchenbar, Obstkuchenbar, Obsttortebar, Serializable
{
    protected Kuchenbar tempKuchen;

    @Serial
    private static final long serialVersionUID = 10L;

    public BelagDekorator(Kuchenbar newKuchen)
    {
        tempKuchen = newKuchen;
    }

    public Kuchenbar getTempKuchen()
    {
        return tempKuchen;
    }
    public Hersteller getHersteller()
    {
        return tempKuchen.getHersteller();
    }
    public void setHersteller(Hersteller hersteller)
    {
        tempKuchen.setHersteller(hersteller);
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
    public void setInspektionsdatum(Date inspektionsdatum)
    {
        tempKuchen.setInspektionsdatum(inspektionsdatum);
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
    public String getKremsorte()
    {
        return ((Kremkuchenbar)tempKuchen).getKremsorte();
    }

    @Override
    public String getObstsorte()
    {
        return ((Obstkuchenbar)tempKuchen).getObstsorte();
    }
}
