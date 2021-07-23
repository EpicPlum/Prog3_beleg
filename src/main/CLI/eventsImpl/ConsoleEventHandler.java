package main.CLI.eventsImpl;

import main.CLI.events.*;

import java.util.LinkedList;
import java.util.List;

public class ConsoleEventHandler
{
    private List<ConsoleEventListener_Imp> listenList;

    public ConsoleEventHandler()
    {
        listenList = new LinkedList<>();
    }

    public boolean add(ConsoleEventListener_Imp listener)
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
    public boolean remove(ConsoleEventListener_Imp listener)
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
    public void handleCapacity(CapacityEvent event)
    {
        for(ConsoleEventListener_Imp listener : listenList)
        {
            listener.onCapacityEvent(event);
        }
    }

    public int handleIntInput(IntInputEvent event)
    {
        for(ConsoleEventListener_Imp listener : listenList)
        {
            return listener.onIntInputEvent(event);
        }

        return -1;
    }

    public String handleInput(InputEvent event)
    {
        for(ConsoleEventListener_Imp listener : listenList)
        {
            return listener.onInputEvent(event);
        }

        return null;
    }

    public void handleArrayInput(ArrayInputEvent event)
    {
        for(ConsoleEventListener_Imp listener : listenList)
        {
            listener.onArrayInputEvent(event);
        }
    }

    public void handleMenu(MenuEvent event)
    {
        for(ConsoleEventListener_Imp listener : listenList)
        {
            listener.onMenuEvent(event);
        }
    }
}
