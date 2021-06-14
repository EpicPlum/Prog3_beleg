package main.CLI.eventsImpl;

import main.CLI.events.InputEvent;

import java.util.LinkedList;
import java.util.List;

public class InputEventHandler
{
    private List<InputEventListener> listenList = new LinkedList<>();

    public boolean add(InputEventListener listener)
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
    public boolean remove(InputEventListener listener)
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
    public void handle(InputEvent event)
    {
        for(InputEventListener listener : listenList)
        {
            listener.onInputEvent(event);
        }
    }
}
