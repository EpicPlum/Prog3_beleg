package main.Simulation;

import main.GL.*;
import main.GL.interfaces.Allergen;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class addThread extends automatThread
{
    private static Kuchen added;
    private static Hersteller hTest = new Hersteller("hTest");

    public addThread(Automat automat, int mode)
    {
        super(automat, mode);
        automat.addHersteller(hTest);
    }



    @Override
    public void run()
    {
        while(true)
        {
            if (getMode() == 1)
            {
                    getLock().lock();
                    try {
                        addRandom();
                    } finally {
                        getLock().unlock();
                    }
            }
            else if (getMode() == 2)
            {
                    try {
                        addRandomInspection();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }

    }
    public synchronized Kuchen addRandom()
    {
        if(getAutomat().size() == getAutomat().maxSize())
        {
            return null;
        }
            //adds randomly generated Kuchen to Automat
            int kuchenArt = ThreadLocalRandom.current().nextInt(1, 5);

            added = getRandomKuchen(kuchenArt);

            getAutomat().add(added);
            System.out.println("Fuegt zufaellig Kuchen ein. --- " + added);

            return added;
    }

    public Kuchen addRandomInspection() throws InterruptedException
    {
        synchronized (getMonitor()) {
                while (getAutomat().size() >= getAutomat().maxSize()) {
                    //threadActivity.stopAdding();
                    //threadActivity.startRemoving();
                    System.out.println("Automat ist voll.");
                    removeThread.getMonitor().notify();
                    addThread.getMonitor().wait();
                    break;
                }
                //adds randomly generated Kuchen to Automat
                int kuchenArt = ThreadLocalRandom.current().nextInt(1, 5);

                added = getRandomKuchen(kuchenArt);

                getAutomat().add(added);

                System.out.println("Fuegt zufaellig Kuchen ein. --- " + added + " --- "+ Thread.currentThread().getName());

        }
        return added;
    }

    public static Kuchen getRandomKuchen(int art) throws IllegalArgumentException
    {
        if(art < 0 || art > 4)
        {
            throw new IllegalArgumentException("Ungueltig Art von Kuchen.");
        }

        Kuchen k;
        BigDecimal preis = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.0,99.99)).round(MathContext.DECIMAL32);
        Date inspektionsdatum = new Date(getRandom().nextLong());
        Collection<Allergen> allergene = getRandomAllergene();
        int naehrwert = ThreadLocalRandom.current().nextInt(1,2001);
        Duration haltbarkeit = Duration.ofDays(ThreadLocalRandom.current().nextInt(1,51));
        String kremsorte = UUID.randomUUID().toString();
        String obstsorte = UUID.randomUUID().toString();

        if(art == 1)
        {
            k = new Kuchen(preis, inspektionsdatum, hTest, naehrwert, allergene, haltbarkeit);
            return k;
        }
        else if(art == 2)
        {
            k = new Kremkuchen(preis, inspektionsdatum, hTest, naehrwert, allergene, haltbarkeit, kremsorte);
            return k;
        }
        else if(art == 3)
        {
            k = new Obstkuchen(preis, inspektionsdatum, hTest, naehrwert, allergene, haltbarkeit, obstsorte);
            return k;
        }
        else if(art == 4)
        {
            k = new Obsttorte(preis, inspektionsdatum, hTest, naehrwert, allergene, haltbarkeit, kremsorte, obstsorte);
            return k;
        }
        else
            return k = new Kuchen();
    }

    public static Collection<Allergen> getRandomAllergene()
    {
        List<Allergen> allergene = Arrays.asList(Allergen.values());
        Collections.shuffle(allergene);

        HashSet<Allergen> randAllergene = new HashSet<Allergen>();
        int randAmount = ThreadLocalRandom.current().nextInt(0,allergene.size());

        for(int i = 0; i < randAmount; i++)
        {
            randAllergene.add(allergene.get(i));
        }
        
        return randAllergene;
    }
}
