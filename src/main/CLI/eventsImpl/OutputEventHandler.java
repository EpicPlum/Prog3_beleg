package main.CLI.eventsImpl;

import main.CLI.events.OutputEvent;

import java.util.LinkedList;
import java.util.List;

public class OutputEventHandler
{
    private List<OutputEventListener> listenList = new LinkedList<>();

    public boolean add(OutputEventListener listener)
    {
        try
        {
            this.listenList.add(listener);
        }
        catch(UnsupportedOperationException e)
        {
            return false;
        }

        return true;
    }
    public boolean remove(OutputEventListener listener)
    {
        try
        {
            this.listenList.remove(listener);
        }
        catch(UnsupportedOperationException e)
        {
            return false;
        }

        return true;
    }
    public void handle(OutputEvent event)
    {
        for(OutputEventListener listener : listenList)
        {
            listener.onOutputEvent(event);
        }
    }
}
