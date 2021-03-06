package main.GL.interfaces;

import main.GL.Hersteller;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;

public interface Kuchenbar extends Verkaufsobjektbar, Serializable
{
    Hersteller getHersteller();
    Collection<Allergen> getAllergene();
    int getNaehrwert();
    Duration getHaltbarkeit();
    String getName();
    void setHersteller(Hersteller hersteller);
    void setHaltbarkeit(Duration haltbarkeit);
    public String belagToString();
    @Serial
    static final long serialVersionUID = 22L;
}
