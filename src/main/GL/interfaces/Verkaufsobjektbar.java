package main.GL.interfaces;

import java.math.BigDecimal;
import java.util.Date;

public interface Verkaufsobjektbar {
    BigDecimal getPreis();
    Date getInspektionsdatum();
    int getFachnummer();
    void setFachnummer(int fachnummer);
    String toString();
}
