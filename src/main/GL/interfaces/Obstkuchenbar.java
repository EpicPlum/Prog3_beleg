package main.GL.interfaces;

import java.io.Serial;
import java.io.Serializable;

public interface Obstkuchenbar extends Kuchenbar,Verkaufsobjektbar, Serializable {
    String getObstsorte();
    @Serial
    static final long serialVersionUID = 23L;
}
