import main.GL.*;
import main.GL.interfaces.Allergen;
import main.Simulation.addThread;
import main.Simulation.automatThread;
import main.Simulation.inspectionThread;
import main.Simulation.removeThread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class simulationTest
{

    private Automat auto;
    private main.Simulation.inspectionThread inspectionThread;
    private main.Simulation.addThread addThread;
    private removeThread removeThread;
    private Kuchen testKuchen1;
    private Kuchen testKuchen2;
    private Kuchen testKuchen3;
    private Kuchen testKuchen4;
    private Kuchen testKuchen5;
    private Kuchen testKuchen6;
    private Kremkuchen kremkuchenNormal;
    private Obstkuchen obstkuchenNormal;
    Hersteller jacobs;

    //---------------------------------------------------------------------------------------
    /*
    Mockito
     */


    @BeforeEach
    public void setUp()
    {
        jacobs = new Hersteller("Jacobs");
        auto = new Automat(10);
        auto.addHersteller(jacobs);

        testKuchen1 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen2 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen3 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen4 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen5 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        testKuchen6 = new Kuchen(BigDecimal.ONE, new Date(2021, 04, 19), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(3));
        kremkuchenNormal = new Kremkuchen(BigDecimal.ONE, new Date(2021, 04, 22), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(5), "Sahne");
        obstkuchenNormal = new Obstkuchen(BigDecimal.ONE, new Date(2021, 04, 22), jacobs, 250, new ArrayList<Allergen>(), Duration.ofDays(5), "Erdbeer");

        inspectionThread = new inspectionThread(auto, 1);

        addThread = new addThread(auto, 1);

        removeThread = new removeThread(auto, 1);

    }

    /*----------------------------------------------------------------------------------------------------------------------
    getRandomFachnummer()
    ---------------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void randomFachNummerNegativ()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> automatThread.getRandomFachnummer(-1));
    }
    @Test
    public void randomFachnummerAutomatSize()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> automatThread.getRandomFachnummer(auto.size()));
    }
    @Test
    public void randomFachnummerNormal()
    {
        auto.add(kremkuchenNormal);
        assertEquals(inspectionThread.getAutomat().getFachNummern().get(0), inspectionThread.getRandomFachnummer(0));
    }

    /*----------------------------------------------------------------------------------------------------------------------
    getRandomKuchen()
     ---------------------------------------------------------------------------------------------------------------------------*/
    @Test
    public void randomKuchenNegativ()
    {
        assertThrows(IllegalArgumentException.class, () -> addThread.getRandomKuchen(-1));
    }
    @Test
    public void randomKuchenKuchen()
    {
        assertTrue(addThread.getRandomKuchen(1) instanceof Kuchen);
    }
    @Test
    public void randomKuchenKremkuchen()
    {
        assertTrue(addThread.getRandomKuchen(2) instanceof Kremkuchen);
    }


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
