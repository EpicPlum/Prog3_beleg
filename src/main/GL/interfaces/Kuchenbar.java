package main.GL.interfaces;

import java.time.Duration;
import java.util.Collection;

public interface Kuchenbar extends Verkaufsobjektbar
{
    Herstellerbar getHersteller();
    Collection<Allergen> getAllergene();
    int getNaehrwert();
    Duration getHaltbarkeit();
    String getName();
}
