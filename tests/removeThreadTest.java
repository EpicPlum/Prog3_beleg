
import main.GL.*;
import main.GL.interfaces.Allergen;
import main.Simulation.removeThread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class removeThreadTest
{
    private Automat auto;
    private main.Simulation.removeThread removeThread;
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


        testKuchen1 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstelle"), 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen2 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstellz"), 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen3 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstelli"), 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen4 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstellop"), 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen5 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstellio"), 250, new HashSet<Allergen>(), Duration.ofDays(3));
        testKuchen6 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), new Hersteller("Herstelln"), 250, new HashSet<Allergen>(), Duration.ofDays(3));
        kremkuchenNormal = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), new Hersteller("Jacksons"), 250, new HashSet<Allergen>(), Duration.ofDays(5), "Sahne");
        obstkuchenNormal = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), new Hersteller("Johnsons"), 250, new HashSet<Allergen>(), Duration.ofDays(5), "Erdbeer");




        removeThread = new removeThread(auto, 1);

    }

    /*------------------------------------------------------------------------------------------------------------------
    Mockito Testen
    --------------------------------------------------------------------------------------------------------------------- */



    /*----------------------------------------------------------------------------------------------------------------------
    findOldestInpection()
     ---------------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void oldestInspectionNull()
    {
        assertThrows(NullPointerException.class, () -> removeThread.findOldestInspection());
    }
    @Test
    public void oldestInspectionSize1()
    {
        auto.add(testKuchen3);
        auto.setInspektionsdatum(151182, new Date(472754324));

        assertEquals(testKuchen3.getFachnummer(), removeThread.findOldestInspection());
    }
    @Test
    public void oldestInspectionSize4()
    {
        auto.add(testKuchen2);
        auto.add(testKuchen4);
        auto.add(testKuchen5);
        auto.add(kremkuchenNormal);
        auto.setInspektionsdatum(15482, new Date(47664525));
        auto.setInspektionsdatum(145682, new Date(275));
        auto.setInspektionsdatum(35681, new Date(475));

        assertEquals(testKuchen5.getFachnummer(), removeThread.findOldestInspection());
    }

}
