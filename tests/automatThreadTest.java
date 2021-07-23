
import main.GL.*;
import main.GL.interfaces.Allergen;
import main.Simulation.automatThread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class automatThreadTest
{
    private Automat auto;
    private automatThread autoThread;
    private Kuchen testKuchen1;
    private Kuchen testKuchen2;
    private Kuchen testKuchen3;
    private Kuchen testKuchen4;
    private Kuchen testKuchen5;
    private Kuchen testKuchen6;
    private Kremkuchen kremkuchenNormal;
    private Obstkuchen obstkuchenNormal;

    //---------------------------------------------------------------------------------------
    /*
    Mockito
     */


    @BeforeEach
    public void setUp()
    {
        auto = new Automat(10);


        testKuchen1 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstelle"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen2 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstellz"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen3 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstelli"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen4 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstellop"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen5 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstellio"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen6 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstelln"), 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        kremkuchenNormal = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), new Hersteller("Jacksons"), 250, new ArrayList<Allergen>(), Duration.ofDays(5), "Sahne");
        obstkuchenNormal = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), new Hersteller("Johnsons"), 250, new ArrayList<Allergen>(), Duration.ofDays(5), "Erdbeer");

        autoThread = new automatThread(auto, 1)
        {
            @Override
            public void run()
            {
                //leer
            }
        };

    }

    /*------------------------------------------------------------------------------------------------------------------
    Mockito Testen
    --------------------------------------------------------------------------------------------------------------------- */



    /*----------------------------------------------------------------------------------------------------------------------
    getRandomFachnummer()
     ---------------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void randomFachNummerNegativ()
    {
        auto.add(testKuchen1);
        auto.add(testKuchen2);
        assertThrows(IndexOutOfBoundsException.class, () -> automatThread.getRandomFachnummer(-1));
    }
    @Test
    public void randomFachnummerAutomatSize()
    {
        auto.add(testKuchen3);
        auto.add(testKuchen4);
        assertThrows(IndexOutOfBoundsException.class, () -> automatThread.getRandomFachnummer(auto.size()));
    }
    @Test
    public void randomFachnummerNormal()
    {
        auto.add(kremkuchenNormal);
        assertEquals(autoThread.getAutomat().getFachNummern().get(0), autoThread.getRandomFachnummer(0));
    }

}
