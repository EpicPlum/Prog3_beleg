package main.GL.dekorator;

import main.GL.Hersteller;
import main.GL.interfaces.*;

import java.util.Date;

public abstract class BelagDekorator implements Verkaufsobjektbar, Kuchenbar, Kremkuchenbar, Obstkuchenbar, Obsttortebar
{
    protected Kuchenbar tempKuchen;

    public BelagDekorator()
    {
        tempKuchen = null;
    }

    public BelagDekorator(Kuchenbar newKuchen)
    {
        tempKuchen = newKuchen;
    }

    public Hersteller getHersteller()
    {
        return tempKuchen.getHersteller();
    }
    public void setHersteller(Hersteller hersteller)
    {
        tempKuchen.setHersteller(hersteller);
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
