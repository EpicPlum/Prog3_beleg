package main.CLI.observerPatternImpl;

import main.CLI.observerPattern.Observer;

public class Observer_Impl implements Observer
{
    private Observable_Impl observable;
    private int oldState;
    private String name;

    public Observer_Impl(String name)
    {
        this.name = name;
    }
    @Override
    public void update()
    {
        int newState = observable.getState();
        if(newState != this.oldState)
        {
            System.out.println("New state: " + newState);
            this.oldState = newState;
        }

    }
}
