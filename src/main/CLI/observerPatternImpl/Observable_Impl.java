package main.CLI.observerPatternImpl;

import main.CLI.observerPattern.Observable;
import main.CLI.observerPattern.Observer;

import java.util.LinkedList;
import java.util.List;

public class Observable_Impl implements Observable
{
    private List<Observer> observerList;
    private int state;

    public Observable_Impl()
    {
        observerList = new LinkedList<>();
    }

    public int getState()
    {
        return state;
    }

    public List<Observer> getList()
    {
        return observerList;
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
