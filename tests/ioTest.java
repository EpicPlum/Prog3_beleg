import main.CLI.console;
import main.GL.*;
import main.GL.interfaces.Allergen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;
import java.util.Date;

import static main.IO.jos.loadAutomat;
import static main.IO.jos.saveAutomat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ioTest {
    private Hersteller herstello;
    private Hersteller herstella;

    private Kuchen kuchenNormal;
    private Kuchen testKuchen1;
    private Kuchen testKuchen2;
    private Kuchen testKuchen3;
    private Kuchen testKuchen4;
    private Kuchen testKuchen5;
    private Kuchen testKuchen6;
    private Kremkuchen kremkuchenNormal;
    private Obstkuchen obstkuchenNormal;
    private HashSet<Allergen> ge;
    private HashSet<Allergen> hs;

    private Automat auto;
    private Automat auto2;

    private main.CLI.console console;


    //---------------------------------------------------------------------------------------
    /*
    Mockito
     */
    @Mock
    private console consoleMock;


    @BeforeEach
    public void setUp() {
        auto = new Automat(5);
        console = new console(auto);
        auto2 = new Automat(5);

        hs = new HashSet<Allergen>();
        hs.add(Allergen.Haselnuss);
        hs.add(Allergen.Sesamsamen);
        ge = new HashSet<Allergen>();
        ge.add(Allergen.Gluten);
        ge.add(Allergen.Erdnuss);

        herstello = new Hersteller("Herstello");
        herstella = new Hersteller("Herstella");

        kuchenNormal = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstello, 250, new HashSet<Allergen>(), Duration.ofDays(3));

        testKuchen1 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstello, 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen2 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstello, 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen3 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstello, 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen4 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstello, 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen5 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstella, 250, ge, Duration.ofDays(3));
        testKuchen6 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), herstella, 250, new HashSet<Allergen>(), Duration.ofDays(3));

        kremkuchenNormal = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), herstella, 250, hs, Duration.ofDays(5), "Sahne");

        obstkuchenNormal = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), herstella, 250, ge, Duration.ofDays(5), "Erdbeer");


        auto.addHersteller(herstella);
        auto2.addHersteller(herstella);
        auto.add(kremkuchenNormal);
        auto2.add(kremkuchenNormal);

        saveAutomat("josTest.ser", auto2);

        consoleMock = mock(console.class);


    }

    @Test
    public void saveJOSRichtigFileTest()
    {
        assertTrue(saveAutomat("josTest.ser", auto));
    }

    @Test
    public void saveJOSFalscheFileTest()
    {
        assertFalse(saveAutomat("", auto));
    }

    @Test
    public void saveJOSNullTest()
    {
        assertThrows(NullPointerException.class, () -> saveAutomat("josTest.ser", null));
    }

    @Test
    public void loadJOSRichtigFileTest()
    {
        assertEquals(auto2.listVerkaufsobjekte(1), loadAutomat("josTest.ser").listVerkaufsobjekte(1));
    }

    @Test
    public void loadJOSFalscheFileTest()
    {
        assertEquals(null, loadAutomat(""));
    }
}




