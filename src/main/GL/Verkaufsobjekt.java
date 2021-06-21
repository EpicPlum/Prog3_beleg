package main.GL;

import main.GL.interfaces.Verkaufsobjektbar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
/*
Verkaufsobjekte man findet im Automat
 */
public class Verkaufsobjekt implements Verkaufsobjektbar, Serializable
{
    /*
    Instanzvariablen
     */
    private BigDecimal preis;
    private Date inspektionsdatum;
    private int fachnummer;
    /*
    Default Konstruktor
     */
    public Verkaufsobjekt()
    {
        preis = new BigDecimal(0.0);
        inspektionsdatum = new Date(2);
        fachnummer = 0;
    }
    /*
    Vollstaendig Konstruktor
     */
    public Verkaufsobjekt(BigDecimal preis, Date inspektionsdatum, int fachnummer) throws NullPointerException, IllegalArgumentException
    {
        if(preis == null || inspektionsdatum == null)
        {
            throw new NullPointerException("Werte darf nicht null sein.");
        }


        this.preis = preis;
        this.inspektionsdatum = inspektionsdatum;
        this.fachnummer = fachnummer;
    }

    @Override
    public BigDecimal getPreis()
    {
        return preis;
    }

    public void setPreis(BigDecimal preis)
    {
        this.preis = preis;
        this.preis.setScale(4, RoundingMode.CEILING);
    }

    @Override
    public Date getInspektionsdatum()
    {
        return inspektionsdatum;
    }
    /*
    Inspektionsdatum Setter
    Kann nicht in die Zukunft sein
     */
    public void setInspektionsdatum(Date inspektionsdatum) throws NullPointerException
    {
        if(inspektionsdatum == null)
        {
            throw new NullPointerException("Inspektionsdatum darf nicht null sein.");
        }

        this.inspektionsdatum = inspektionsdatum;
    }

    @Override
    public int getFachnummer()
    {
        return fachnummer;
    }

    public void setFachnummer(int fachnummer)
    {
        this.fachnummer = fachnummer;
    }

    @Override
    public String toString()
    {
        return "Preis: " + getPreis() + " Euro" + " - Inspektionsdatum: " + getInspektionsdatum() + " - Fachnummer: " + getFachnummer();
    }
}
