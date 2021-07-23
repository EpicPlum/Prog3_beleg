package main.GL;

import main.GL.dekorator.Kuchenboden;
import main.GL.interfaces.Allergen;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

/*
Kuchen im Automat, erben von Verkaufsobjekt
 */
public class Kuchen extends Kuchenboden implements Serializable
{
    /*
    Instanzvariablen
     */
    @Serial
    private static final long serialVersionUID = 4L;


    public Kuchen()
    {
        super();
    }
    /*
    Vollstaendig Konstruktor
     */

    public Kuchen(BigDecimal preis, Date inspektionsdatum, Hersteller hersteller, int naehrwert, Collection<Allergen> allergene, Duration haltbarkeit) throws NullPointerException
    {
        super(preis, inspektionsdatum, hersteller, naehrwert, allergene, haltbarkeit);
    }
}
