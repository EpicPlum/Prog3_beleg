import main.GL.*;
import main.GL.interfaces.Allergen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class AutomatTest
{


    private Verkaufsobjekt vkDefault;
    private Verkaufsobjekt vkNormal;
    private Verkaufsobjekt vkNull;
    private Verkaufsobjekt vkFachnummer;

    private Kuchen kuchenDefault;
    private Kuchen kuchenNormal;
    private Kuchen kuchenNull;

    private Kremkuchen kremkuchenDefault;
    private Kremkuchen kremkuchenNormal;
    private Kremkuchen kremkuchenNull;

    private Obstkuchen obstkuchenDefault;
    private Obstkuchen obstkuchenNormal;
    private Obstkuchen obstkuchenNull;

    private Hersteller herstellerDefault;
    private Hersteller herstellerNormal;
    private Hersteller herstellerNull;

    private ArrayList<Allergen> ge;
    private ArrayList<Allergen> hs;

    private Automat auto;
    private Automat auto2;

    //---------------------------------------------------------------------------------------
    /*
    Mockito
     */
    @Mock
    private Verkaufsobjekt vkMock;
    private Kuchen kuchenMock;
    private Automat autoMock;

    @BeforeEach
    public void setUp()
    {
        vkMock = Mockito.mock(Verkaufsobjekt.class);
        kuchenMock = Mockito.mock(Kuchen.class);
        autoMock = Mockito.mock(Automat.class);

        hs = new ArrayList<Allergen>();
        hs.add(Allergen.Haselnuss);
        hs.add(Allergen.Sesamsamen);
        ge = new ArrayList<Allergen>();
        ge.add(Allergen.Gluten);
        ge.add(Allergen.Erdnuss);

        vkDefault = new Verkaufsobjekt();
        vkNormal = new Verkaufsobjekt(BigDecimal.ONE, new Date(2021, 04, 22), 14681);

        kuchenDefault = new Kuchen();
        kuchenNormal = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15682, new Hersteller("Herstello"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));

        kremkuchenDefault = new Kremkuchen();
        kremkuchenNormal = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 35681, new Hersteller("Jacksons"), 250, hs, Duration.ofDays(5), "Sahne");

        obstkuchenDefault = new Obstkuchen();
        obstkuchenNormal = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 24681, new Hersteller("Johnsons"), 250, hs, Duration.ofDays(5), "Erdbeer");

        auto = new Automat(10);
        auto2 = new Automat(10);

        auto2.add(new Verkaufsobjekt(BigDecimal.ONE, new Date(2021, 04, 22), 14688));
        autoMock.add(vkMock);

    }

    /*------------------------------------------------------------------------------------------------------------------
    Mockito Testen
    --------------------------------------------------------------------------------------------------------------------- */
    @Test
    public void addNormalMock()
    {
        assertNotNull(autoMock);
        autoMock.add(kuchenMock);
        when(autoMock.size()).thenReturn(2);
        assertEquals(2, autoMock.size());
    }

    @Test
    public void addNullMock()
    {
        assertNotNull(autoMock);
        doThrow(new NullPointerException()).when(autoMock).add(null);
        assertThrows(NullPointerException.class, () -> autoMock.add(null));
    }

    /*------------------------------------------------------------------------------------------------------------------
    Verkaufsobjekt
     ------------------------------------------------------------------------------------------------------------------*/

    @Test
    public void vkDefaultTest()
    {
        assertEquals(vkDefault.getFachnummer(), 0);
        assertTrue(vkDefault instanceof Verkaufsobjekt);
    }

    @Test
    public void vkNormalTest()
    {
        assertEquals(vkNormal.getFachnummer(), 14681);
        assertTrue(vkNormal instanceof Verkaufsobjekt);
    }

    @Test
    public void vkNullTest()
    {
        assertThrows(NullPointerException.class, () -> vkNull = new Verkaufsobjekt(null, null, 14681));
    }

    /*------------------------------------------------------------------------------------------------------------------
    Kuchen
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void kuchenDefaultTest()
    {
        assertEquals(kuchenDefault.getNaehrwert(), 0);
        assertTrue(kuchenDefault instanceof Kuchen);
    }

    @Test
    public void kuchenNormalTest()
    {
        assertEquals(kuchenNormal.getFachnummer(), 15682);
        assertTrue(kuchenNormal instanceof Kuchen);
    }

    @Test
    public void kuchenNullTest()
    {
        assertThrows(NullPointerException.class, () -> kuchenNull = new Kuchen(null, new Date(2021, 04, 19), 15682, null, 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
    }

    /*------------------------------------------------------------------------------------------------------------------
    Kremkuchen
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void kremkuchenDefaultTest()
    {
        assertEquals(kremkuchenDefault.getKremsorte(),"");
        assertTrue(kremkuchenDefault instanceof Kremkuchen);
    }

    @Test
    public void kremkuchenNormalTest()
    {
        assertEquals(kremkuchenNormal.getFachnummer(), 35681);
        assertTrue(kremkuchenNormal instanceof Kuchen);
    }

    @Test
    public void kremkuchenNullTest()
    {
        assertThrows(NullPointerException.class, () -> kremkuchenNull = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 32681, new Hersteller("Jacksons"), 250, hs, Duration.ofDays(5), null));
    }

    /*------------------------------------------------------------------------------------------------------------------
    Obstkuchen
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void obstkuchenDefaultTest()
    {
        assertEquals(obstkuchenDefault.getObstsorte(),"");
        assertTrue(obstkuchenDefault instanceof Obstkuchen);
    }

    @Test
    public void obstkuchenNormalTest()
    {
        assertEquals(obstkuchenNormal.getFachnummer(), 24681);
        assertTrue(obstkuchenNormal instanceof Obstkuchen);
    }

    @Test
    public void obstkuchenNullTest()
    {
        assertThrows(NullPointerException.class, () -> obstkuchenNull = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 24481, new Hersteller("Johnsons"), 250, hs, Duration.ofDays(5), null));
    }

    /*------------------------------------------------------------------------------------------------------------------
    Automat
     ------------------------------------------------------------------------------------------------------------------*/


    /*------------------------------------------------------------------------------------------------------------------
    add()
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void addEmpty()
    {
        auto.add(vkDefault);
        assertEquals(auto.size(), 1);
    }

    @Test
    public void addNormal()
    {
        auto.add(vkDefault);
        auto.add(vkNormal);
        assertEquals(auto.size(), 2);
    }

    @Test
    public void addNull()
    {
        assertThrows(NullPointerException.class, () -> auto.add(null));
    }

    @Test
    public void addKuchen()
    {
        auto.add(kuchenDefault);
        assertEquals(auto.size(), 1);
    }
    @Test
    public void addKremkuchen()
    {
        auto.add(kremkuchenDefault);
        assertEquals(auto.size(), 1);
    }
    @Test
    public void addObstkuchen()
    {
        auto.add(obstkuchenDefault);
        assertEquals(auto.size(), 1);
    }
    @Test
    public void addSchonHersteller()
    {
        auto.add(kremkuchenNormal);
        assertThrows(IllegalArgumentException.class, () -> auto.add(kremkuchenNormal));
    }
    /*------------------------------------------------------------------------------------------------------------------
    removeKuchen()
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void removeKuchenNegativ()
    {
        auto.add(kuchenNormal);
        assertThrows(IllegalArgumentException.class, () -> auto.removeKuchen(-1));
    }

    @Test
    public void removeKuchenNormal()
    {
        auto.add(kuchenNormal);
        auto.add(obstkuchenNormal);

    }

    @Test
    public void removeKuchenLeer()
    {
        auto.removeKuchen(15682);
        assertEquals(auto.size(), 0);
    }

    @Test
    public void removeKuchenKeineFachnummer()
    {
        auto.add(kuchenNormal);
        auto.removeKuchen(27);
        assertEquals(auto.size(), 1);
    }

    @Test
    public void removeKuchenVk()
    {
        auto.add(vkNormal);
        auto.removeKuchen(14681);
        assertEquals(auto.size(), 0);
    }
    /*------------------------------------------------------------------------------------------------------------------
    removeHersteller()
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void removeHerstellerNull()
    {
        auto2.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15482, new Hersteller("Jimmys"), 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
        auto2.add(new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 3561, new Hersteller("Billys"), 250, hs, Duration.ofDays(5), "Sahne"));
        auto2.add(new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 24331, new Hersteller("Boboys"), 250, hs, Duration.ofDays(5), "Erdbeer"));
        assertThrows(NullPointerException.class, () -> auto2.removeHersteller(null));
    }

    @Test
    public void removeHerstellerNormal()
    {
        auto2.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15482, new Hersteller("Jammys"), 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
        auto2.add(new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 3561, new Hersteller("Bellys"), 250, hs, Duration.ofDays(5), "Sahne"));
        auto2.add(new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 24331, new Hersteller("Bobbys"), 250, hs, Duration.ofDays(5), "Erdbeer"));
        auto2.removeHersteller("Herstello");
        assertEquals(auto2.getHerstellern().size(), 3);
    }

    @Test
    public void removeHerstellerLeer()
    {
        Automat auto3 = new Automat(6);
        auto3.removeHersteller("Billys");
        assertEquals(auto3.size(), 0);
    }

    @Test
    public void removeHerstellerNonexistierend()
    {
        auto2.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15482, new Hersteller("Jioommys"), 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
        auto2.add(new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 3561, new Hersteller("Bioollys"), 250, hs, Duration.ofDays(5), "Sahne"));
        auto2.add(new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 24331, new Hersteller("Boys"), 250, hs, Duration.ofDays(5), "Erdbeer"));
        auto2.removeHersteller("pizza");
        assertEquals(auto2.size(), 4);
    }
    /*------------------------------------------------------------------------------------------------------------------
    listAllergene()
     ------------------------------------------------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------------------------------------------------
    setInspektionsDatum
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void setIDNull()
    {
        assertThrows(NullPointerException.class, () -> auto2.setInspektionsdatum(0, null));
    }

    @Test
    public void setIDVk()
    {
        auto2.setInspektionsdatum(vkDefault.getFachnummer(), new Date());
        assertEquals(new Date(2021, 04, 20), vkDefault.getInspektionsdatum());
    }

    @Test
    public void setIDKuchen()
    {
        auto2.setInspektionsdatum(kuchenDefault.getFachnummer(), new Date());
        assertEquals(new Date(2021, 04, 20), kuchenDefault.getInspektionsdatum());
    }


}
