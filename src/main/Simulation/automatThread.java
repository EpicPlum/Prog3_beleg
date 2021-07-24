package main.Simulation;

import main.GL.Automat;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public abstract class automatThread extends Thread
{
    private static Automat automat;
    private static final Object monitor = new Object();
    private static Random random = new Random();
    private int mode;
    private ReentrantLock lock = new ReentrantLock();


    public automatThread(Automat automat, int mode) throws IllegalArgumentException
    {
        if(mode != 1 && mode != 2 && mode != 3)
        {
            throw new IllegalArgumentException("Falsche Modus.");
        }

        this.automat = automat;
        this.mode = mode;
    }
    public static Automat getAutomat()
    {
        return automat;
    }
    public static Object getMonitor()
    {
        return monitor;
    }
    public int getMode()
    {
        return mode;
    }
    public ReentrantLock getLock()
    {
        return lock;
    }
    public static Random getRandom()
    {
        return random;
    }

    public abstract void run();
    public static int getRandomFachnummer(int pos) throws IndexOutOfBoundsException
    {
        if(pos < 0 || pos > getAutomat().size())
        {
            throw new IndexOutOfBoundsException("Ungueltige Stelle.");
        }

        int fachnummer = 0;

        for(int i = 0; i <= pos; i++)
        {
            fachnummer = getAutomat().getFachNummern().get(pos);
        }

        return fachnummer;
    }
}
