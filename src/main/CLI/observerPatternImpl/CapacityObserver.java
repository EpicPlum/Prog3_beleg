package main.CLI.observerPatternImpl;

import main.CLI.console;

/*
Observer
 */
public class CapacityObserver extends Observer_Impl
{

    public CapacityObserver(console console, String name)
    {
        super(console, name);
        console.addObserver(this);
    }

    @Override
    public boolean update()
    {
        //if capacity is 90% or greater, send message and return true
        //else, message and return false
        //capacity = 10 Kuchen, so 90% = 9 Kuchen

        if(getConsole().getAutomat().size() >= (getConsole().getAutomat().maxSize() * 0.9))
        {
            System.out.println("Automat ist 90% oder mehr voll.");
            return true;
        }
        else
        {
            //System.out.println("Automat ist " + (getConsole().getAutomat().size())*10 + "% voll.");
            return false;
        }
    }

}
