package main.GL.interfaces;

import main.GL.Hersteller;

import java.time.Duration;
import java.util.Collection;

public interface Kuchenbar extends Verkaufsobjektbar
{
    Hersteller getHersteller();
    Collection<Allergen> getAllergene();
    int getNaehrwert();
    Duration getHaltbarkeit();
    String getName();
    void setHersteller(Hersteller hersteller);
    void setHaltbarkeit(Duration haltbarkeit);
}
