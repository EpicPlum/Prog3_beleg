package main.GL.dekorator;

import main.GL.interfaces.Allergen;
import main.GL.interfaces.Herstellerbar;
import main.GL.interfaces.Kuchenbar;
import main.GL.interfaces.Verkaufsobjektbar;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public abstract class BelagDekorator implements Verkaufsobjektbar, Kuchenbar, Serializable
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

    }
}
