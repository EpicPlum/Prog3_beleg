package main.CLI.eventsImpl;

import main.CLI.events.*;

public class ConsoleEventListener_Imp implements ConsoleEventListener
{
    private String name;

    public ConsoleEventListener_Imp(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public void onCapacityEvent(CapacityEvent event) throws IllegalArgumentException
    {
        if(event.getNum() > 0 && event.getAutomat() != null)
        {
            event.getAutomat().setMaxSize(event.getNum());
        }
        else
            throw new IllegalArgumentException("Ungueltig int und Automat.");
    }

    @Override
    public int onIntInputEvent(IntInputEvent event)
    {
        return event.getNum();
    }

    @Override
    public String onInputEvent(InputEvent event)
    {
        return event.getText();
    }

    @Override
    public void onArrayInputEvent(ArrayInputEvent event) throws NullPointerException
    {
        if(event.getText() != null)
        {
            event.setArray(event.getText().split(" "));
        }
        else
            throw new NullPointerException("String ist null");
    }

    @Override
    public void onMenuEvent(MenuEvent event) throws NullPointerException
    {
        if(event.getText() != null)
        {
            event.getConsole().setMenuEingabe(event.getText());
        }
        else
            throw new NullPointerException("Text ist null.");

    }


}
