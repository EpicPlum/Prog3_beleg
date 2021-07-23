package main.Simulation;

import main.GL.*;

import java.util.concurrent.ThreadLocalRandom;

public class removeThread extends automatThread
{
    private static int removedFachnummer = 0;


    public removeThread(Automat automat, int mode)
    {
        super(automat, mode);
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
                    removeRandom();
                } finally {
                    getLock().unlock();
                }
            }
            else if (getMode() == 2)
            {
                    try {
                        removeOldestInpection();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            else if (getMode() == 3)
            {
                try {
                    removeOldestInpectionRandomAmount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized static int removeRandom(){
            //removes randomly selected Kuchen from Automat
            //try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

            if (getAutomat().size() == 0) {
                //System.out.println("Automat ist leer. Elemente koennen nicht entfernt werden.");
                return 0;
            }

            int pos = ThreadLocalRandom.current().nextInt(0, getAutomat().size());
            removedFachnummer = getRandomFachnummer(pos);


            getAutomat().removeKuchen(removedFachnummer);
            System.out.println("Entfernt zufaellig Kuchen. --- Fachnummer: " + removedFachnummer);
            return removedFachnummer;
    }

    public int removeOldestInpection() throws InterruptedException
    {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            synchronized (getMonitor()) {
                while (threadActivity.isRemoving()) {
                    try {
                        while (getAutomat().size() <= 0) {
                            threadActivity.stopRemoving();
                            threadActivity.startAdding();
                            System.out.println("Automat ist leer. Elemente koennen nicht entfernt werden.");
                            addThread.getMonitor().notify();
                            removeThread.getMonitor().wait();
                            //return 0;
                        }
                        removedFachnummer = findOldestInspection();
                    }
                    catch (NullPointerException e) {
                        threadActivity.startRemoving();
                    //removes Kuchen from Automat with oldest inspection date

                    }

                    getAutomat().removeKuchen(removedFachnummer);
                    System.out.println("Entfernt Kuchen mit aeltesten Inspektionsdatum. --- Fachnummer: " + removedFachnummer + " --- " + Thread.currentThread().getName());

                }
                return removedFachnummer;
            }

    }

    public int removeOldestInpectionRandomAmount() throws InterruptedException
    {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (getMonitor()) {
            int amount = ThreadLocalRandom.current().nextInt(0, getAutomat().size()+1);
            for(int i = 0; i < amount && getAutomat().size() <= amount; i++)
            {
            while (threadActivity.isRemoving()) {
                try {
                    while (getAutomat().size() <= 0) {
                        threadActivity.stopRemoving();
                        threadActivity.startAdding();
                        //System.out.println("Automat ist leer. Elemente koennen nicht entfernt werden.");
                        addThread.getMonitor().notify();
                        removeThread.getMonitor().wait();
                        break;
                    }
                    removedFachnummer = findOldestInspection();
                } catch (NullPointerException e) {
                    threadActivity.startRemoving();
                    //removes Kuchen from Automat with oldest inspection date

                }

                getAutomat().removeKuchen(removedFachnummer);
                System.out.println("Entfernt Kuchen mit aeltesten Inspektionsdatum. --- Fachnummer: " + removedFachnummer + " --- " + Thread.currentThread().getName());
            }
            }
            return removedFachnummer;
        }

    }



    //Finds cake with oldest inspection date and returns associated fachnummer
    public int findOldestInspection() throws NullPointerException
    {

        if(getAutomat().isEmpty())
        {
            throw new NullPointerException("Automat ist leer.");
        }

        Node loop = getAutomat().getHead();
        long oldest = ((Kuchen)loop.getData()).getInspektionsdatum().getTime();
        int oldestFachnummer = ((Kuchen)loop.getData()).getFachnummer();

        while(loop != null)
        {
            if(((Kuchen)loop.getData()).getInspektionsdatum().getTime() <= oldest)
            {
                oldest = ((Kuchen)loop.getData()).getInspektionsdatum().getTime();
                oldestFachnummer = ((Kuchen)loop.getData()).getFachnummer();
            }
            loop = loop.getNext();
        }
        return oldestFachnummer;
    }
}
