package main.GL.interfaces;

import java.io.Serial;
import java.io.Serializable;

public interface Herstellerbar extends Serializable {
    String getName();
    void incrementCountKuchen(int amount);
    void decrementCountKuchen(int amount);
    @Serial
    static final long serialVersionUID = 20L;
}
