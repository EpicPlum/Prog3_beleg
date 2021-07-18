package main.CLI.eventsImpl;

import main.CLI.events.*;

public class ConsoleEventListener_Imp implements ConsoleEventListener
{
    private String name;

    public ConsoleEventListener_Imp(String name)
    {
        this.name = name;
    }
    @Override
    public void onCapacityEvent(CapacityEvent event)
    {
        if(event.getNum() > 0 && event.getAutomat() != null)
        {
            event.getAutomat().setMaxSize(event.getNum());
        }
    }

    @Override
    public void onIntInputEvent(IntInputEvent event)
    {

    }

    @Override
    public void onInputEvent(InputEvent event)
    {
        //event.getText().split(" ");
    }

    @Override
    public void onArrayInputEvent(ArrayInputEvent event)
    {
        if(event.getText() != null)
        {
            event.setArray(event.getText().split(" "));
        }
    }

    @Override
    public void onMenuEvent(MenuEvent event)
    {
        event.getConsole().setMenuEingabe(event.getText());
    }


}
