package main.GL.interfaces;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public interface Verkaufsobjektbar extends Serializable {
    BigDecimal getPreis();
    Date getInspektionsdatum();
    int getFachnummer();
    void setFachnummer(int fachnummer);
    String toString();
    void setInspektionsdatum(Date inspektionsdatum);
    @Serial
    long serialVersionUID = 9L;
}
