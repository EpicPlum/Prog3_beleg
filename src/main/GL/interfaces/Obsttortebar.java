package main.GL.interfaces;

import java.io.Serial;
import java.io.Serializable;

public interface Obsttortebar extends Obstkuchenbar,Kremkuchenbar, Kuchenbar, Serializable
{
    @Serial
    static final long serialVersionUID = 24L;
}
