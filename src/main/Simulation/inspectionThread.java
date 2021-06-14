package main.Simulation;

import main.GL.*;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class inspectionThread extends automatThread
{
    private static int inspectedFachnummer;
    private static Date inspectedDate;

    public inspectionThread(Automat automat, int mode)
    {
        super(automat, mode);
    }

    @Override
    public void run()
    {
        while(true)
        {
            System.out.println("Loest zufaellige Inspektion aus. --- Fachnummer: " + inspectRandom());
        }
    }

    //inspects random element from automat by getting a random fachnummer and a random date
    public static int inspectRandom()
    {
        synchronized (getMonitor()) {
            //inspects random cake in automat through use of a random fachnummer
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getAutomat().size() == 0)
            {
                System.out.println("Keine Kuchen zu inspektieren.");
                return 0;
            }

            int pos = ThreadLocalRandom.current().nextInt(0, getAutomat().size());
            inspectedFachnummer = getRandomFachnummer(pos);
            inspectedDate = new Date(getRandom().nextLong());

            getAutomat().setInspektionsdatum(inspectedFachnummer, inspectedDate);
            return inspectedFachnummer;
        }
        }
 }
