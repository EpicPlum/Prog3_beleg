import main.GL.*;
import main.GL.interfaces.Allergen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

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

    private Obsttorte obsttorteDefault;
    private Obsttorte obsttorteNormal;

    private Hersteller johnsons;
    private Hersteller jacobs;
    private Hersteller herstello;

    private ArrayList<Allergen> ge;
    private ArrayList<Allergen> hs;

    private Node nodeTest;

    private Automat auto;
    private Automat auto2;
    private Automat auto3;

    //---------------------------------------------------------------------------------------
    /*
    Mockito
     */
    @Mock
    private Hersteller herstellerMock;
    private Kuchen kuchenMock;
    private Automat autoMock;

    @Spy
    private Automat autoSpy;
    private Hersteller herstellerSpy;
    private Kuchen kuchenSpy;

    @BeforeEach
    public void setUp()
    {
        kuchenMock = Mockito.mock(Kuchen.class);
        autoMock = Mockito.mock(Automat.class);
        herstellerMock = Mockito.mock(Hersteller.class);

        autoSpy = Mockito.spy(new Automat());
        herstellerSpy = Mockito.spy(new Hersteller());
        kuchenSpy = Mockito.spy(new Kuchen());

        johnsons = new Hersteller("Johnsons");
        jacobs = new Hersteller("Jacobs");
        herstello = new Hersteller("Herstello");

        hs = new ArrayList<Allergen>();
        hs.add(Allergen.Haselnuss);
        hs.add(Allergen.Sesamsamen);
        ge = new ArrayList<Allergen>();
        ge.add(Allergen.Gluten);
        ge.add(Allergen.Erdnuss);

        vkDefault = new Verkaufsobjekt();
        vkNormal = new Verkaufsobjekt(BigDecimal.ONE, new Date(2021, 04, 22));
        kuchenDefault = new Kuchen();
        kuchenNormal = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstello, 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        kremkuchenDefault = new Kremkuchen();
        kremkuchenNormal = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), jacobs, 250, hs, Duration.ofDays(5), "Sahne");
        obstkuchenDefault = new Obstkuchen();
        obstkuchenNormal = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5), "Erdbeer");
        obsttorteDefault = new Obsttorte();
        obsttorteNormal = new Obsttorte(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5), "Erdbeer", "Sahne");

        nodeTest = new Node();

        auto = new Automat(10);
        auto.addHersteller(herstello);
        auto.addHersteller(jacobs);
        auto.addHersteller(johnsons);

        auto2 = new Automat(10);
        auto2.addHersteller(johnsons);

        auto3 = new Automat();
        auto3.addHersteller(johnsons);

        auto2.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5)));
        autoMock.addHersteller(johnsons);
        autoSpy.addHersteller(johnsons);

/*
        auto.add(vkNormal);

        auto.add(kuchenDefault);

        auto.add(kremkuchenDefault);

        auto.add(obstkuchenDefault);
        //auto.add(obstkuchenNormal);

*/
    }

    /*------------------------------------------------------------------------------------------------------------------
    Mockito Testen
    --------------------------------------------------------------------------------------------------------------------- */
    @Test
    public void addNormalMock()
    {
        when(kuchenMock.getHersteller()).thenReturn(johnsons);
        auto.add(kuchenMock);
        assertEquals(1, auto.size());
    }

    @Test
    public void addNullMock()
    {
        doThrow(new NullPointerException()).when(autoMock).add(null);
        assertThrows(NullPointerException.class, () -> autoMock.add(null));
    }

    @Test
    public void addHerstellerMock()
    {
        when(herstellerMock.getName()).thenReturn("Johnsons");
        auto.addHersteller(herstellerMock);
        assertEquals(4, auto.getHerstellern().size());
    }

    @Test
    public void spyAddTest()
    {
        autoSpy.add(obstkuchenNormal);
        Mockito.verify(autoSpy).add(obstkuchenNormal);
        assertEquals(1, autoSpy.size());
    }

    @Test
    public void spyRemoveTest()
    {
        autoSpy.add(obstkuchenNormal);
        autoSpy.removeKuchen(0);
        Mockito.verify(autoSpy).removeKuchen(0);
        assertEquals(0, autoSpy.size());
    }

    @Test
    public void spyAddHerstellerTest()
    {
        autoSpy.addHersteller(jacobs);
        Mockito.verify(autoSpy).addHersteller(jacobs);
        assertEquals(2, autoSpy.getHerstellern().size());
    }

    @Test
    public void spyRemoveHerstellerTest()
    {
        autoSpy.removeHersteller("Johnsons");
        Mockito.verify(autoSpy).removeHersteller("Johnsons");
        assertEquals(0, autoSpy.getHerstellern().size());
    }









    //Hersteller
    @Test
    public void setNameTest()
    {
        herstello.setName("hallo");
        assertEquals("hallo", herstello.getName());
    }

    @Test
    public void countKuchenTest()
    {
        assertEquals(0, herstello.countKuchen());
    }

    @Test
    public void herstellerCompareToTest()
    {
        assertEquals(0, herstello.compareTo(herstello));
    }

    /*------------------------------------------------------------------------------------------------------------------
    Verkaufsobjekt
     ------------------------------------------------------------------------------------------------------------------*/

    @Test
    public void vkDefaultTest()
    {
        assertTrue(vkDefault instanceof Verkaufsobjekt);
    }

    @Test
    public void vkNormalTest()
    {
        assertTrue(vkNormal instanceof Verkaufsobjekt);
    }

    @Test
    public void vkNullTest()
    {
        assertThrows(NullPointerException.class, () -> vkNull = new Verkaufsobjekt(null, null));
    }

    @Test
    public void setPreisTest()
    {
        vkNormal.setPreis(new BigDecimal(100));
        assertEquals(new BigDecimal(100), vkNormal.getPreis());
    }

    @Test
    public void setIDNullTest()
    {
        assertThrows(NullPointerException.class, () -> vkNormal.setInspektionsdatum(null));
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
        assertEquals(kuchenNormal.getNaehrwert(), 250);
        assertTrue(kuchenNormal instanceof Kuchen);
    }

    @Test
    public void kuchenNullTest()
    {
        assertThrows(NullPointerException.class, () -> kuchenNull = new Kuchen(null, new Date(2021, 04, 19), null, 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
    }

    @Test
    public void kuchenHerstellerNullTest()
    {
        assertThrows(NullPointerException.class, () -> kuchenNull = new Kuchen(new BigDecimal(0), new Date(2021, 04, 19), null, 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
    }

    @Test
    public void setHerstellerTest()
    {
        kuchenNormal.setHersteller(herstello);
        assertEquals(herstello, kuchenNormal.getHersteller());
    }

    @Test
    public void setAllergeneTest()
    {
        kuchenNormal.setAllergene(ge);
        assertEquals(ge, kuchenNormal.getAllergene());
    }

    @Test
    public void setNaehrwertTest()
    {
        kuchenNormal.setNaehrwert(200);
        assertEquals(200, kuchenNormal.getNaehrwert());
    }

    @Test
    public void setHaltbarkeitTest()
    {
        kuchenNormal.setHaltbarkeit(Duration.ofDays(5));
        assertEquals(Duration.ofDays(5), kuchenNormal.getHaltbarkeit());
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
        assertEquals(kremkuchenNormal.getKremsorte(),"Sahne");
        assertTrue(kremkuchenNormal instanceof Kuchen);
    }

    @Test
    public void kremkuchenNullTest()
    {
        assertThrows(NullPointerException.class, () -> kremkuchenNull = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), new Hersteller("Jacksons"), 250, hs, Duration.ofDays(5), null));
    }

    @Test
    public void setKremsorteTest()
    {
        kremkuchenNormal.setKremsorte("Vanille");
        assertEquals("Vanille", kremkuchenNormal.getKremsorte());
    }

    @Test
    public void kremkuchenToStringTest()
    {
        assertEquals("Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 0 " +
                "- Hersteller: Jacobs - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5" +
                " Tage - Kremsorte: Sahne", kremkuchenNormal.toString());
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
        assertEquals(obstkuchenNormal.getObstsorte(),"Erdbeer");
        assertTrue(obstkuchenNormal instanceof Obstkuchen);
    }

    @Test
    public void obstkuchenNullTest()
    {
        assertThrows(NullPointerException.class, () -> obstkuchenNull = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), new Hersteller("Johnsons"), 250, hs, Duration.ofDays(5), null));
    }

    @Test
    public void setObstsorteTest()
    {
        obstkuchenNormal.setObstsorte("Kiwi");
        assertEquals("Kiwi", obstkuchenNormal.getObstsorte());
    }

    @Test
    public void obstkuchenToStringTest()
    {
        assertEquals("Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - " +
                "Fachnummer: 0 - Hersteller: Johnsons - Allergene: [Haselnuss, Sesamsamen] - " +
                "Naehrwert: 250 - Haltbarkeit: 5 Tage - Obstsorte: Erdbeer", obstkuchenNormal.toString());
    }

    //Obsttorte

    @Test
    public void obsttorteDefaultTest()
    {
        assertEquals(obsttorteDefault.getObstsorte(),"");
        assertTrue(obsttorteDefault instanceof Obsttorte);
    }

    @Test
    public void obsttorteNormalTest()
    {
        assertEquals(obsttorteNormal.getObstsorte(),"Erdbeer");
        assertTrue(obsttorteNormal instanceof Obsttorte);
    }

    @Test
    public void getKremsorteTest()
    {
        assertEquals("Sahne", obsttorteNormal.getKremsorte());
    }


    @Test
    public void ot_setKremsorteTest()
    {
        obsttorteNormal.setKremsorte("Vanille");
        assertEquals("Vanille", obsttorteNormal.getKremsorte());
    }

    @Test
    public void obsttorteToStringTest()
    {
        assertEquals("Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 0 - Hersteller: Johnsons - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250" +
                " - Haltbarkeit: 5 Tage - Obstsorte: Erdbeer - Kremsorte: Sahne", obsttorteNormal.toString());
    }


    /*------------------------------------------------------------------------------------------------------------------
    Automat
     ------------------------------------------------------------------------------------------------------------------*/

    //Node

    @Test
    public void NodeConstructorTest()
    {
        assertTrue(nodeTest instanceof Node);
    }

    @Test
    public void getDataTest()
    {
        assertEquals(null, nodeTest.getData());
    }

    @Test
    public void getNextTest()
    {
        assertEquals(null, nodeTest.getNext());
    }

    //Automat - Objekt

    @Test
    public void automatConstructorTest()
    {
        assertTrue(auto3 instanceof Automat);
    }

    @Test
    public void getHeadTest()
    {
        assertEquals(null, auto3.getHead());
    }

    @Test
    public void setSizeTest()
    {
        auto3.setSize(10);
        assertEquals(10, auto3.size());
    }

    @Test
    public void maxSizeTest()
    {
        assertEquals(5, auto3.maxSize());
    }

    @Test
    public void getNumAllergeneTest()
    {
        assertEquals(0, auto3.getNumAllergene());
    }

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
        auto.add(vkNormal);
        assertEquals(auto.size(), 1);
    }

    @Test
    public void addNull()
    {
        assertThrows(NullPointerException.class, () -> auto.add(null));
    }

    @Test
    public void addFull()
    {
        auto3.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5)));
        auto3.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5)));
        auto3.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5)));
        auto3.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5)));
        auto3.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5)));
        assertThrows(IndexOutOfBoundsException.class, () -> auto3.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5))));
    }

    @Test
    public void addKuchen()
    {
        auto.add(kuchenNormal);
        assertEquals(auto.size(), 1);
    }
    @Test
    public void addKremkuchen()
    {
        auto.add(kremkuchenNormal);
        assertEquals(auto.size(), 1);
    }
    @Test
    public void addObstkuchen()
    {
        auto.add(obstkuchenNormal);
        assertEquals(auto.size(), 1);
    }
    @Test
    public void addObsttorte()
    {
        auto.add(obsttorteNormal);
        assertEquals(auto.size(), 1);
    }

    @Test
    public void addExistingFachnummer()
    {
        auto.add(kuchenNormal);
        auto.add(obstkuchenNormal);
        auto.getFachNummern().set(0, 2);
        assertThrows(IllegalArgumentException.class, () -> auto.add(kremkuchenNormal));
    }

    @Test
    public void addKeinHersteller()
    {
        assertThrows(IllegalArgumentException.class, () -> auto3.add(kuchenNormal));
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
        assertThrows(IndexOutOfBoundsException.class, () -> auto.removeKuchen(0));
    }

    @Test
    public void removeKuchenKeineFachnummer()
    {
        auto.add(kuchenNormal);
        auto.removeKuchen(2);
        assertEquals(auto.size(), 0);
    }

    @Test
    public void removeKuchenVk()
    {
        auto.add(vkNormal);
        auto.removeKuchen(0);
        assertEquals(auto.size(), 0);
    }

    //addHersteller()

    @Test
    public void addHerstellerNormal()
    {
        auto.addHersteller(new Hersteller("Knorr"));
        assertEquals(auto.getHerstellern().size(), 4);
    }

    /*------------------------------------------------------------------------------------------------------------------
    removeHersteller()
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void removeHerstellerNull()
    {
        auto2.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), johnsons, 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
        auto2.add(new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5), "Sahne"));
        auto2.add(new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5), "Erdbeer"));
        assertThrows(NullPointerException.class, () -> auto2.removeHersteller(null));
    }

    @Test
    public void removeHerstellerNormal()
    {
        auto2.add(new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), johnsons, 250, new ArrayList<Allergen>(), Duration.ofDays(3)));
        auto2.add(new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5), "Sahne"));
        auto2.add(new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), johnsons, 250, hs, Duration.ofDays(5), "Erdbeer"));
        auto2.removeHersteller("Johnsons");
        assertEquals(auto2.getHerstellern().size(), 0);
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
        auto2.removeHersteller("pizza");
        assertEquals(auto2.getHerstellern().size(), 1);
    }

    //listVerkaufsobjekte

    @Test
    public void lv0Test()
    {
        assertEquals("", auto2.listVerkaufsobjekte(0));
    }

    @Test
    public void lv1Test()
    {
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 0 - Hersteller: Johnsons - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - " +
                "Haltbarkeit: 5 Tage ***\n", auto2.listVerkaufsobjekte(1));
    }

    @Test
    public void lv2Test()
    {
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 0 - Hersteller: Johnsons " +
                "- Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage ***\n", auto2.listVerkaufsobjekte(2));
    }

    @Test
    public void lv3Test()
    {
        auto.add(kremkuchenNormal);
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 0 - Hersteller: Jacobs - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage" +
                " - Kremsorte: Sahne ***\n", auto.listVerkaufsobjekte(3));
    }

    @Test
    public void lv4Test()
    {
        auto2.add(obstkuchenNormal);
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 1 - Hersteller: Johnsons - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - " +
                "Haltbarkeit: 5 Tage - Obstsorte: Erdbeer ***\n", auto2.listVerkaufsobjekte(4));
    }

    @Test
    public void lv5Test()
    {
        auto2.add(obsttorteNormal);
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 1 - Hersteller: Johnsons - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage - Obstsorte: Erdbeer " +
                "- Kremsorte: Sahne ***\n", auto2.listVerkaufsobjekte(5));
    }

    //listHersteller

    @Test
    public void lhSize0Test()
    {
        auto3.removeHersteller("Johnsons");
        assertEquals("Keine Herstellern", auto3.listHersteller());
    }

    @Test
    public void lhSize1Test()
    {
        assertEquals("Johnsons 1\n", auto3.listHersteller());
    }

    /*------------------------------------------------------------------------------------------------------------------
    listAllergene()
     ------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void la0Test()
    {
        assertThrows(IllegalArgumentException.class, () -> auto.listAllergene(0));
    }

    @Test
    public void la1Test()
    {
        auto.add(obsttorteNormal);
        assertEquals("Vorhandene Allergene im Automat: [Haselnuss, Sesamsamen]", auto.listAllergene(1));
    }

    @Test
    public void la2Test()
    {
        auto.add(obsttorteNormal);
        assertEquals("Nicht vorhandene Allergene im Automat: [Gluten, Erdnuss]", auto.listAllergene(2));
    }
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
        assertEquals(new Date(0), vkDefault.getInspektionsdatum());
    }

    @Test
    public void setIDKuchen()
    {
        auto2.setInspektionsdatum(kuchenDefault.getFachnummer(), new Date());
        assertEquals(new Date(0), kuchenDefault.getInspektionsdatum());
    }

    //findNumAllergene

    @Test
    public void fna0Test()
    {
        assertEquals(0, auto3.findNumAllergene());
    }

    @Test
    public void fna2Test()
    {
        auto.add(kremkuchenNormal);
        assertEquals(2, auto.findNumAllergene());
    }

    //fachNummerSort

    @Test
    public void fnSortLeer()
    {
        assertFalse(auto3.fachnummerSort());
    }

    @Test
    public void fnSortNormal()
    {
        auto.add(kuchenNormal);
        auto.add(kremkuchenNormal);
        auto.add(obsttorteNormal);
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Thu May 19 00:00:00 CEST 3921 - Fachnummer: 0 - Hersteller: Herstello - Allergene: [] - Naehrwert: 250 - Haltbarkeit: 3 Tage ***\n" +
                "*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 1 - Hersteller: Jacobs - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage - Kremsorte: Sahne ***\n" +
                "*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 2 - Hersteller: Johnsons - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage - Obstsorte: Erdbeer - Kremsorte: Sahne ***\n", auto.listVerkaufsobjekte(2));
    }

    //herstellerSort

    @Test
    public void herstellerSortLeer()
    {
        assertFalse(auto3.herstellerSort());
    }

    @Test
    public void herstellerSortNormal()
    {
        auto.add(kuchenNormal);
        auto.add(kremkuchenNormal);
        auto.add(obsttorteNormal);
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Thu May 19 00:00:00 CEST 3921 - Fachnummer: 0 - Hersteller: Herstello" +
                " - Allergene: [] - Naehrwert: 250 - Haltbarkeit: 3 Tage ***\n" +
                "*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 1 - Hersteller: Jacobs" +
                " - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage - Kremsorte: Sahne ***\n" +
                "*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 2 - Hersteller: Johnsons " +
                "- Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage - Obstsorte: Erdbeer - Kremsorte: Sahne ***\n", auto.listVerkaufsobjekte(2));
    }

    //haltbarkeitSort

    @Test
    public void hkSortLeer()
    {
        assertFalse(auto3.haltbarkeitSort());
    }

    @Test
    public void hkSortNormal()
    {
        auto.add(kuchenNormal);
        auto.add(kremkuchenNormal);
        auto.add(obsttorteNormal);
        assertEquals("*** Preis: 1 Euro - Inspektionsdatum: Thu May 19 00:00:00 CEST 3921 - Fachnummer: 0 - Hersteller: Herstello - Allergene: [] - Naehrwert: 250 - Haltbarkeit: 3 Tage ***\n" +
                "*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 1 - Hersteller: Jacobs - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage - Kremsorte: Sahne ***\n" +
                "*** Preis: 1 Euro - Inspektionsdatum: Sun May 22 00:00:00 CEST 3921 - Fachnummer: 2 - Hersteller: Johnsons - Allergene: [Haselnuss, Sesamsamen] - Naehrwert: 250 - Haltbarkeit: 5 Tage - Obstsorte: Erdbeer - Kremsorte: Sahne ***\n", auto.listVerkaufsobjekte(2));
    }
}
