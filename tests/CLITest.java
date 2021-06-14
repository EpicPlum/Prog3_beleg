package tests;

import main.CLI.observerPatternImpl.AllergenObserver;
import main.CLI.observerPatternImpl.CapacityObserver;
import main.CLI.observerPatternImpl.ObservableAllergen;
import main.CLI.observerPatternImpl.ObservableCapacity;
import main.GL.*;
import main.GL.interfaces.Allergen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


public class CLITest
{

    private Kuchen kuchenNormal;
    private Kuchen testKuchen1;
    private Kuchen testKuchen2;
    private Kuchen testKuchen3;
    private Kuchen testKuchen4;
    private Kuchen testKuchen5;
    private Kuchen testKuchen6;


    private Kremkuchen kremkuchenNormal;

    private Obstkuchen obstkuchenNormal;

    private ArrayList<Allergen> ge;
    private ArrayList<Allergen> hs;

    private Automat auto;
    private Automat auto2;

    private ObservableCapacity capacity;
    private CapacityObserver capObserver;
    private ObservableAllergen allergene;
    private AllergenObserver alObserver;


    //---------------------------------------------------------------------------------------
    /*
    Mockito
     */
    @Mock
    private ObservableCapacity capacityMock;
    private CapacityObserver capObserverMock;
    private ObservableAllergen allergeneMock;
    private AllergenObserver alObserverMock;

    @BeforeEach
    public void setUp()
    {
        auto = new Automat(5);
        auto2 = new Automat(5);

        hs = new ArrayList<Allergen>();
        hs.add(Allergen.Haselnuss);
        hs.add(Allergen.Sesamsamen);
        ge = new ArrayList<Allergen>();
        ge.add(Allergen.Gluten);
        ge.add(Allergen.Erdnuss);

        kuchenNormal = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15682, new Hersteller("Herstello"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));

        testKuchen1 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15282, new Hersteller("Herstelle"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen2 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 15482, new Hersteller("Herstellz"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen3 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 151182, new Hersteller("Herstelli"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen4 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 152, new Hersteller("Herstellop"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen5 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 145682, new Hersteller("Herstellio"), 250, ge, Duration.ofDays(3));
        testKuchen6 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), 1582, new Hersteller("Herstelln"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));

        kremkuchenNormal = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 35681, new Hersteller("Jacksons"), 250, hs, Duration.ofDays(5), "Sahne");

        obstkuchenNormal = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), 24681, new Hersteller("Johnsons"), 250, hs, Duration.ofDays(5), "Erdbeer");

        auto.add(kremkuchenNormal);

        capacity = new ObservableCapacity(0);
        capObserver = new CapacityObserver(capacity,"capObserver");

        allergene = new ObservableAllergen(0);
        alObserver = new AllergenObserver(allergene,"alObserver");


        capacityMock = mock(ObservableCapacity.class);
        capObserverMock = mock(CapacityObserver.class);

        allergeneMock = mock(ObservableAllergen.class);
        alObserverMock = mock(AllergenObserver.class);




    }

    /*------------------------------------------------------------------------------------------------------------------
    Mockito Testen
    --------------------------------------------------------------------------------------------------------------------- */
    @Test
    public void capObMock()
    {
        assertFalse(capacityMock.checkCapacity(auto));
    }

    @Test
    public void allObMock()
    {
        assertFalse(allergeneMock.checkAllergens(auto2));
    }

    /*@Test
    //public void capEventMock()
    {
        assertFalse(capacitySourceMock.checkCapacity(auto2));
    }

    /*@Test
    public void allEventMock()
    {
        assertFalse(allergeneSourceMock.checkAllergens(auto2));
    }

    /*----------------------------------------------------------------------------------------------------------------------
    Capacity Observer
     ---------------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void capObTestWeniger90()
    {
        assertFalse(capacity.checkCapacity(auto));
    }

    @Test
    public void capObTest90Percent()
    {
        auto.add(kuchenNormal);
        auto.add(obstkuchenNormal);
        auto.add(testKuchen1);
        auto.add(testKuchen2);
        auto.add(testKuchen3);
        auto.add(testKuchen4);
        auto.add(testKuchen5);
        auto.add(testKuchen6);
        assertTrue(capacity.checkCapacity(auto));
    }

    /*----------------------------------------------------------------------------------------------------------------------
    Allergene Observer
     ---------------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void allObTestAendert()
    {
        auto.removeKuchen(35681);
        assertFalse(allergene.checkAllergens(auto));
    }

    @Test
    public void allObTestNichtAendert()
    {
        assertTrue(allergene.checkAllergens(auto));
    }

    /*----------------------------------------------------------------------------------------------------------------------
    checkCapacityEvent
     ---------------------------------------------------------------------------------------------------------------------------*/

    /*@Test
    public void capEventTestWeniger90()
    {
        assertFalse(capacitySource.checkCapacity(auto2));
    }
    */
    @Test
    public void capEventTest90Percent()
    {
        auto2.add(kuchenNormal);
        auto2.add(kremkuchenNormal);
        auto2.add(obstkuchenNormal);
        auto2.add(testKuchen1);
        auto2.add(testKuchen2);
        auto2.add(testKuchen3);
        auto2.add(testKuchen4);
        auto2.add(testKuchen5);
        auto2.add(testKuchen6);
        //assertTrue(capacitySource.checkCapacity(auto2));

    }


    /*----------------------------------------------------------------------------------------------------------------------
    allergenEvent
     ---------------------------------------------------------------------------------------------------------------------------*/

    /*@Test
    public void allEventTestAendert()
    {
        auto.removeKuchen(35681);
        assertFalse(allergeneSource.checkAllergens(auto));
    }

    @Test
    public void allEventTestNichtAendert()
    {
        assertTrue(allergeneSource.checkAllergens(auto));
    }
*/
}
