package main.CLI.observerPatternImpl;

import main.GL.Automat;

/*
Subject
 */
public class ObservableCapacity extends ObservableCounter
{

    public ObservableCapacity(int startCapacity)
    {
        super(startCapacity);
    }

    public boolean checkCapacity(Automat automat)
    {
        //if capacity is 90% or greater, send message and return true
        //else, message and return false
        //capacity = 10 Kuchen, so 90% = 9 Kuchen

        if(automat.size() >= 9)
        {
            System.out.println("Automat ist 90% oder mehr voll.");
            return true;
        }
        else
        {
            System.out.println("Automat ist " + (automat.size())*10 + "% voll.");
            return false;
        }
    }
}
