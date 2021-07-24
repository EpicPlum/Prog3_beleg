package main.GL.interfaces;

import java.io.Serial;
import java.io.Serializable;

public interface Kremkuchenbar extends Kuchenbar,Verkaufsobjektbar, Serializable
{
    String getKremsorte();
    @Serial
    static final long serialVersionUID = 21L;
}
