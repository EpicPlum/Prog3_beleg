package main.CLI.observerPatternImpl;

import main.CLI.observerPattern.Observer;
/*
Observer
 */
public class CounterObserver implements Observer
{
    private ObservableCounter counter;
    private int oldState;
    private String name;

    public CounterObserver(String name)
    {
        this.name = name;
    }
    @Override
    public void update()
    {
        int newState = counter.getState();
        if(newState != this.oldState)
        {
            System.out.println("New state: " + newState);
            this.oldState = newState;
        }

    }
}
