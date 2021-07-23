package main.CLI.observerPatternImpl;

import main.CLI.console;
import main.CLI.observerPattern.Observer;

public class Observer_Impl implements Observer
{
    private console console;
    private int oldState;
    private String name;

    public Observer_Impl(console console, String name) throws NullPointerException
    {
        if(console == null || name == null)
        {
            throw new NullPointerException("Console/Name ist null.");
        }

        this.console = console;
        this.name = name;
    }
    @Override
    public boolean update()
    {
        int newState = console.getState();
        if(newState != this.oldState)
        {
            System.out.println("New state: " + newState);
            this.oldState = newState;
            return true;
        }
        else
            return false;

    }

    public console getConsole()
    {
        return console;
    }
}
