package main.CLI.observerPatternImpl;

import main.CLI.domainLogic.Counter;
import main.CLI.observerPattern.Observable;
import main.CLI.observerPattern.Observer;

import java.util.LinkedList;
import java.util.List;
/*
Subject
 */
public class ObservableCounter extends Counter implements Observable
{
    public ObservableCounter(int startValue) {
        super(startValue);
    }
    private List<Observer> observerList = new LinkedList<Observer>();
    private int state;

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
        this.notifyObservers();
    }

    @Override
    public boolean addObserver(Observer observer)
    {
        try
        {
            this.observerList.add(observer);
        }
        catch(UnsupportedOperationException e)
        {
            return false;
        }

        return true;
    }
    @Override
    public boolean removeObserver(Observer observer)
    {
        try
        {
            this.observerList.remove(observer);
        }
        catch(UnsupportedOperationException e)
        {
            return false;
        }

        return true;
    }
    @Override
    public void notifyObservers()
    {
        for(Observer o : this.observerList)
        {
            o.update();
        }
    }
}
