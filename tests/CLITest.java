import main.CLI.console;
import main.CLI.events.*;
import main.CLI.eventsImpl.ConsoleEventHandler;
import main.CLI.eventsImpl.ConsoleEventListener_Imp;
import main.CLI.observerPatternImpl.AllergenObserver;
import main.CLI.observerPatternImpl.CapacityObserver;
import main.GL.*;
import main.GL.interfaces.Allergen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class CLITest
{

    private TextEvent textEvent;
    private InputEvent inputEvent;
    private IntInputEvent intInputEvent;
    private CapacityEvent capacityEvent;
    private MenuEvent menuEvent;
    private ArrayInputEvent arrayInputEvent;
    private String[] array;

    ConsoleEventListener_Imp listener;
    ConsoleEventHandler handler;

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
    private Automat auto1;
    private Automat auto10;

    private CapacityObserver capObserver1;
    private AllergenObserver alObserver;
    private CapacityObserver capObserver10;

    private console console;
    private console console1;
    private console console10;


    //---------------------------------------------------------------------------------------
    /*
    Mockito
     */
    @Mock
    private console consoleMock;
    private CapacityObserver capObserverMock;
    private AllergenObserver alObserverMock;

    @BeforeEach
    public void setUp()
    {
        auto = new Automat(5);
        console = new console(auto);
        auto2 = new Automat(5);
        auto1 = new Automat(1);
        auto10 = new Automat(10);
        console1 = new console(auto1);
        console10 = new console(auto10);
        array = new String[3];

        inputEvent = new InputEvent(this, "");
        intInputEvent = new IntInputEvent(this, 1);
        capacityEvent = new CapacityEvent(this, 1, auto);
        menuEvent = new MenuEvent(this, ":c", console);
        arrayInputEvent = new ArrayInputEvent(this, "", array);

        listener = new ConsoleEventListener_Imp("listener");
        handler = new ConsoleEventHandler();

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
        auto1.addHersteller(herstella);
        auto10.addHersteller(herstella);
        auto.add(kremkuchenNormal);

        auto10.add(kremkuchenNormal);
        auto10.add(kremkuchenNormal);
        auto10.add(kremkuchenNormal);
        auto10.add(kremkuchenNormal);
        auto10.add(kremkuchenNormal);
        auto10.add(kremkuchenNormal);
        auto10.add(kremkuchenNormal);
        auto10.add(kremkuchenNormal);


        capObserver1 = new CapacityObserver(console1,"capObserver1");
        alObserver = new AllergenObserver(console,"alObserver");
        capObserver10 = new CapacityObserver(console10,"capObserver10");


        capObserverMock = mock(CapacityObserver.class);
        consoleMock = mock(console.class);
        alObserverMock = mock(AllergenObserver.class);




    }

    /*------------------------------------------------------------------------------------------------------------------
    Mockito Testen
    --------------------------------------------------------------------------------------------------------------------- */
    @Test
    public void capObMock()
    {

    }

    //TODO
    @Test
    public void allObMock()
    {

    }


    /*----------------------------------------------------------------------------------------------------------------------
    Capacity Observer
     ---------------------------------------------------------------------------------------------------------------------------*/

    @Test
    public void capObMaxSize1Test()
    {
        assertFalse(capObserver1.update());
    }
    @Test
    public void capObMaxSize1AddTest()
    {
        auto1.add(kremkuchenNormal);
        assertTrue(capObserver1.update());
    }
    @Test
    public void capObMaxSize10Test()
    {
        assertFalse(capObserver10.update());
    }
    @Test
    public void capObMaxSize10AddTest()
    {
        auto10.add(kremkuchenNormal);
        assertTrue(capObserver10.update());
    }

    /*----------------------------------------------------------------------------------------------------------------------
    Allergene Observer
     ---------------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void allObNewAllergeneTest()
    {
        auto.add(testKuchen5);
        assertTrue(alObserver.update());
    }

    @Test
    public void allObRemovedKuchenTest()
    {
        auto.removeKuchen(0);
        assertTrue(alObserver.update());
    }

    @Test
    public void allObRemovedHerstellerTest()
    {
        auto.add(obstkuchenNormal);
        assertTrue(alObserver.update());
        auto.removeHersteller("Herstella");
    }

    @Test
    public void allObRemoveCopyTest()
    {
        auto.add(kremkuchenNormal);
        auto.removeKuchen(1);
        assertFalse(alObserver.update());
    }

    //Events

    @Test
    public void InputEventNullTest()
    {
        assertThrows(NullPointerException.class, () -> new InputEvent(this, null));
    }

    @Test
    public void InputEventNormalTest()
    {
        assertEquals("", inputEvent.getText());
    }

    @Test
    public void ArrayInputEventNullTest()
    {
        assertThrows(NullPointerException.class, () -> new ArrayInputEvent(this, "", null));
    }

    @Test
    public void ArrayInputEventNormalTest()
    {
        assertEquals(array, arrayInputEvent.getArray());
    }

    @Test
    public void IntInputEventNormalTest()
    {
        assertEquals(1, intInputEvent.getNum());
    }

    @Test
    public void CapacityEventNullTest()
    {
        assertThrows(NullPointerException.class, () -> new CapacityEvent(this, 1, null));
    }

    @Test
    public void CapacityEventNormalTest()
    {
        assertEquals(auto, capacityEvent.getAutomat());
    }

    @Test
    public void MenuEventNullTest()
    {
        assertThrows(NullPointerException.class, () -> new MenuEvent(this, "", null));
    }

    @Test
    public void MenuEventNormalTest()
    {
        assertEquals(console, menuEvent.getConsole());
    }

    //eventsImpl



    @Test
    public void listenerNormalTest()
    {
        assertEquals("listener", listener.getName());
    }

    @Test
    public void onCapacityEventNormalTest()
    {
        listener.onCapacityEvent(capacityEvent);
        assertEquals(capacityEvent.getAutomat().maxSize(), capacityEvent.getNum());
    }

    @Test
    public void onCapacityEventErrorTest()
    {
        assertThrows(IllegalArgumentException.class, () -> listener.onCapacityEvent(new CapacityEvent(this, -1, auto)));
    }

    @Test
    public void onIntInputEventNormalTest()
    {
        assertEquals(1, listener.onIntInputEvent(intInputEvent));
    }

    @Test
    public void onInputEventNormalTest()
    {
        assertEquals("", listener.onInputEvent(inputEvent));
    }

    @Test
    public void onArrayInputEventNormalTest()
    {
        listener.onArrayInputEvent(arrayInputEvent);
        assertEquals(arrayInputEvent.getText().split(" ")[0], arrayInputEvent.getArray()[0]);
    }

    @Test
    public void onArrayInputEventNullTest()
    {
        assertThrows(NullPointerException.class, () -> listener.onArrayInputEvent(new ArrayInputEvent(this, null, array)));
    }

    @Test
    public void onMenuEventNormalTest()
    {
        listener.onMenuEvent(menuEvent);
        assertEquals(menuEvent.getText(), menuEvent.getConsole().getMenuEingabe());
    }

    @Test
    public void onMenuEventNullTest()
    {
        assertThrows(NullPointerException.class, () -> listener.onMenuEvent(new MenuEvent(this, null, console)));
    }

    @Test
    public void onMenuEventInputMismatchTest()
    {
        assertThrows(InputMismatchException.class, () -> listener.onMenuEvent(new MenuEvent(this, "enter", console)));
    }

    //observerPatternImpl



    //console

    @Test
    public void addHerstellerNullTest()
    {
        assertThrows(NullPointerException.class, () -> console.addModeTest(null));
    }

    @Test
    public void addHerstellerNormalTest()
    {
        assertTrue(console.addModeTest("Jacobs"));
        assertTrue(console.getAutomat().listHersteller().contains("Jacobs 0"));
    }

    @Test
    public void addHerstellerSymbolTest()
    {
        assertThrows(IllegalArgumentException.class, () -> console.addModeTest("@ÜÜ"));
    }

    @Test
    public void displayHerstellerNormalTest()
    {
        assertTrue(console.displayModeTest("hersteller"));
    }

    @Test
    public void displayHerstellerLeerTest()
    {
        assertThrows(InputMismatchException.class, () -> console.displayModeTest(""));
    }

    @Test
    public void displayHerstellerFastTest()
    {
        //assertFalse(console.displayModeTest("hersteller Johnson"));
        assertThrows(InputMismatchException.class, () -> console.displayModeTest("hersteller Johnson"));
    }


    //altConsole

}
